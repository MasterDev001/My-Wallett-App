package com.example.a_common.data

data class WalletData(
    val id: String,
    var name: String = "",
    val balance: Double = 0.0,
    var date: Long = 0
)
