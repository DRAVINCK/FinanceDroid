package com.example.financedroid.data.repositories

import com.example.financedroid.data.models.Transaction
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await


class RemoteRepositoryTransaction: IFinanceRepository {

    private val firestore = FirebaseFirestore.getInstance()
    private val transactionCollection = firestore.collection("transactions")



    override fun getTransactions(): Flow<List<Transaction>>  = callbackFlow {
        val listener = transactionCollection.addSnapshotListener {
            dados, erros ->
            if (erros != null){
                close(erros)
                return@addSnapshotListener
            }
            if (dados != null){
                val transactions = dados.documents.mapNotNull {
                    it.toObject(Transaction::class.java)
                }
                trySend(transactions).isSuccess
            }
        }
        awaitClose { listener.remove()}
    }


    override suspend fun addTransaction(transaction: Transaction) {
        val document: DocumentReference
        if (transaction.uuid.isEmpty()){
            transaction.uuid = transactionCollection.document().id
            document = transactionCollection.document(transaction.uuid)
        }else{
            document = transactionCollection.document(transaction.uuid)
        }
        document.set(transaction).await()
    }

    override suspend fun deleteTransaction(uuid: String) {
        transactionCollection.document(uuid).delete().await()
    }

    override suspend fun updateTransaction(transaction: Transaction) {
        addTransaction(transaction)
    }

    override suspend fun clearTransactions() {
        try {
            transactionCollection.get().addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot.documents) {
                    document.reference.delete()
                }
            }
        } catch (e: Exception) {
            // Lidar com exceções, por exemplo, registrar o erro
            println("Erro ao limpar transações: ${e.message}")
        }
    }

    override suspend fun findTransaction(uuid: String): Transaction {
        return transactionCollection.document(uuid).get().await().toObject(Transaction::class.java)!!

    }


}