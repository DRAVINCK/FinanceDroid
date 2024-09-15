package com.example.financedroid.data

import java.math.BigDecimal
import java.util.Date
import java.util.UUID

data class Transaction(
    var uuid: String = UUID.randomUUID().toString(),
    var category: String= "",
    var value: BigDecimal = BigDecimal.valueOf(0),
    val date: Date = Date()
)
