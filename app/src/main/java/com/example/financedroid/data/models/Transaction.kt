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
    var value: BigDecimal = BigDecimal.valueOf(0),
    val date: Date = Date()
){
    constructor(category: String, value: BigDecimal, date: Date) : this(
        uuid = UUID.randomUUID().toString(),
        category = category,
        value = value,
        date = date
    )
}
