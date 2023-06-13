package com.example.z_entity.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.a_common.data.WalletOwnerData

@Entity(tableName = "walletOwner")
data class MyWalletOwner(
    @PrimaryKey
    var id: String,
    var walletId: String,
    var currencyName: String,
    var currencyBalance: Double,
    var rate: Double
)

fun WalletOwnerData.toMyWalletOwner() =
    MyWalletOwner(id, walletId, currencyName, currencyBalance, rate)

fun MyWalletOwner.toWalletOwnerData() =
    WalletOwnerData(id, walletId, currencyName, currencyBalance, rate)
