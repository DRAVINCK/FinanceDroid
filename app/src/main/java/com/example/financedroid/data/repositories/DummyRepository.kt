package com.example.financedroid.data.repositories

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.financedroid.data.models.Transaction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object DummyRepository : IFinanceRepository {

     var _transactions = mutableListOf<Transaction>()

     val transactions get() = _transactions.toList()
    override fun getTransactions(): Flow<List<Transaction>> {
        return flow {
            emit(transactions) // Emite a lista de transações
        }
    }

    override suspend fun addTransaction(transaction: Transaction) {
        _transactions.add(transaction)
    }

    override suspend fun deleteTransaction(uuid: String) {
        _transactions.removeIf {
            uuid == it.uuid
        }
    }

    override suspend fun updateTransaction(transaction: Transaction) {
        deleteTransaction(transaction.uuid)
        _transactions.add(transaction)
    }

    override suspend fun clearTransactions() {
        _transactions.clear()

    }


    override suspend fun findTransaction(uuid: String): Transaction {
        return transactions.firstOrNull{ it.uuid == uuid} ?: Transaction()
    }



}