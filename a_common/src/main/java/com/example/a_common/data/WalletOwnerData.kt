package com.example.a_common.data

data class WalletOwnerData(
    var id: String,
    var walletId: String = "",
    var currencyId: String = "",
    var currencyBalance: Double = 0.0,
    var rate: Double = 0.0
)