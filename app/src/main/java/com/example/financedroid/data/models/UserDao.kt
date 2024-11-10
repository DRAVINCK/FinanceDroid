package com.example.financedroid.data.models

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("select * from tab_User")
    fun listAll(): Flow<List<User>>

    @Query("select * from tab_User where id = :uuid")
    suspend fun findUser(uuid: String): Flow<User>

    @Upsert
    suspend fun addOrEditUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)
}