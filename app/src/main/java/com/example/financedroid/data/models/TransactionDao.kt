package com.example.financedroid.data.models

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {

    @Query("select * from tab_Transaction")
    suspend fun listAll(): Flow<List<Transaction>>

    @Query("select * from tab_Transaction where uuid = :uuid")
    suspend fun findTransaction(uuid: String): Flow<Transaction>

    @Upsert
    suspend fun addOrEditTransaction(transaction: Transaction)

    @Delete
    suspend fun deleteTransaction(transaction: Transaction)

}