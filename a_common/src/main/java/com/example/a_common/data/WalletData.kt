package com.example.a_common.data

data class WalletData(
    val id: String,
    var name: String = "",
    val walletOwnerDataList: WalletOwnerDataList = WalletOwnerDataList(mutableListOf()),
    var date: Long = 0
)