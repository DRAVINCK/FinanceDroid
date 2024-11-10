package com.example.financedroid.data.repositories

import com.example.financedroid.data.models.Transaction
import kotlinx.coroutines.flow.Flow

interface IFinanceRepository {
    fun getTransactions(): Flow<List<Transaction>>
    suspend fun addTransaction(transaction: Transaction)
    suspend fun deleteTransaction(uuid: String)
    suspend fun updateTransaction(transaction: Transaction)
    suspend fun clearTransactions()
    suspend fun findTransaction(uuid: String): Transaction

}