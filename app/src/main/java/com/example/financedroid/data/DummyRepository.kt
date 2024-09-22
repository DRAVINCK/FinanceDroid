package com.example.financedroid.data

object DummyRepository {
    private var _transactions = mutableListOf<Transaction>()

    val transactions get() = _transactions.toList()

    fun add(transaction: Transaction) {
        _transactions.add(transaction)
    }

    fun deleteTransaction(uuid: String) {
        _transactions.removeIf {
            uuid == it.uuid
        }
    }

    fun updateTransaction(transaction: Transaction) {
        deleteTransaction(transaction.uuid)
        _transactions.add(transaction)
    }

    fun clearTransactions() {
        _transactions.clear()

    }

    fun findTransaction(uuid: String): Transaction {
        return transactions.firstOrNull{ it.uuid == uuid} ?: Transaction()
    }

}