package com.example.financedroid.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.financedroid.data.models.Transaction
import com.example.financedroid.data.models.TransactionDao
import com.example.financedroid.data.models.User
import com.example.financedroid.data.models.UserDao

@Database(entities = [User::class, Transaction::class], version = 1, exportSchema = false)
abstract class FinanceDB : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun transactionDao(): TransactionDao

}

fun openDatabase(context: Context): FinanceDB {
    return Room.databaseBuilder(
        context,
        FinanceDB::class.java,
        "FinanceDB"
    ).build()
}