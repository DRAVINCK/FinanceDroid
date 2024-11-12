package com.example.financedroid.data.repositories

import com.example.financedroid.data.models.User
import kotlinx.coroutines.flow.Flow

interface IUserRepository {

    fun getUsers(): Flow<List<User>>
    suspend fun AddUser(user: User)
    suspend fun deleteUser(uuid: String)
    suspend fun updateUser(user: User)

}