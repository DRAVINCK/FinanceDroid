package com.example.financedroid.data.repositories

import com.example.financedroid.data.models.Transaction
import com.google.firebase.Firebase
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow


class RemoteRepositoryTransactio: IFinanceRepository {

    private val firestore = FirebaseFirestore.getInstance()
    private val transactionCollection = firestore.collection("transactions")

    override var _transactions: MutableList<Transaction>
        get() = TODO("Not yet implemented")
        set(value) {}

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
        TODO("Not yet implemented")
    }

    override suspend fun deleteTransaction(uuid: String) {
        TODO("Not yet implemented")
    }

    override suspend fun updateTransaction(transaction: Transaction) {
        TODO("Not yet implemented")
    }

    override suspend fun clearTransactions() {
        TODO("Not yet implemented")
    }

    override suspend fun findTransaction(uuid: String): Transaction {
        TODO("Not yet implemented")
    }


}