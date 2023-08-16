package com.example.a_common.data

data class CurrencyData(
    val id: String,
    var name: String = "",
    var rate: Double = 0.0,
    var date: Long = 0,
    var balance: Double = 0.0,
)
