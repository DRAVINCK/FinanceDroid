package com.example.financedroid.data.repositories

import com.example.financedroid.data.models.Transaction
import com.example.financedroid.data.models.TransactionDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LocalRepositoryTransaction(
    private val dao: TransactionDao
): IFinanceRepository {
    override var _transactions: MutableList<Transaction>
        get() = TODO("Not yet implemented")
        set(value) {}

    override fun getTransactions(): Flow<List<Transaction>> {
        return dao.listAll()
    }

    override suspend fun addTransaction(transaction: Transaction) {
        return dao.addOrEditTransaction(transaction)
    }

    override suspend fun deleteTransaction(uuid: String) {
        return dao.deleteTransaction(uuid)
    }

    override suspend fun updateTransaction(transaction: Transaction) {
        return dao.addOrEditTransaction(transaction)
    }

    override suspend fun clearTransactions() {
        TODO("Not yet implemented")
    }

    override suspend fun findTransaction(uuid: String): Transaction {
        return dao.findTransaction(uuid)
    }


}