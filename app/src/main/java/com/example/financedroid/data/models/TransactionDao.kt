package com.example.financedroid.data.models

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow


interface TransactionDao {

    fun listAll(): Flow<List<Transaction>>

    suspend fun findTransaction(uuid: String): Transaction

    suspend fun addOrEditTransaction(transaction: Transaction)

    suspend fun deleteTransaction(uuid: String)

}