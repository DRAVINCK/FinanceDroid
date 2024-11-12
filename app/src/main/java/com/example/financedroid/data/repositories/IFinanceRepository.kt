package com.example.financedroid.data.repositories

import com.example.financedroid.data.models.Transaction
import kotlinx.coroutines.flow.Flow

interface IFinanceRepository {
    abstract var _transactions: MutableList<Transaction>
    val transactions get() = _transactions.toList()

    fun getTransactions(): Flow<List<Transaction>>
    suspend fun addTransaction(transaction: Transaction)
    suspend fun deleteTransaction(uuid: String)
    suspend fun updateTransaction(transaction: Transaction)
    suspend fun clearTransactions()
    suspend fun findTransaction(uuid: String): Transaction

}