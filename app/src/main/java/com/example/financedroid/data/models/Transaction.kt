package com.example.financedroid.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal
import java.util.Date
import java.util.UUID

@Entity(tableName = "tab_Transaction")
data class Transaction(
    @PrimaryKey
    var uuid: String = UUID.randomUUID().toString(),
    var category: String= "",
    var value: Double = BigDecimal.valueOf(0).toDouble(),
    val date: Date = Date()
){
    constructor(category: String, value: Double, date: Date) : this(
        uuid = UUID.randomUUID().toString(),
        category = category,
        value = value,
        date = date
    )
}
