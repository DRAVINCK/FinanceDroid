package com.example.financedroid.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tab_User")
data class User(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val email: String,
    val password: String
){
    constructor() : this(null,"","")

}
