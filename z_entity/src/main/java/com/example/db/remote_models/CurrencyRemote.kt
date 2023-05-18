package com.example.db.remote_models


data class CurrencyRemote(
    val id: String,
    var name: String = "",
    var rate: Double = 0.0,
    var date: Long = 0
)

