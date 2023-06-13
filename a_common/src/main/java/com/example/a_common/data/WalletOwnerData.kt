package com.example.a_common.data

data class WalletOwnerData(
    var id: String,
    var walletId: String,
    var currencyName: String,
    var currencyBalance: Double,
    var rate: Double
)