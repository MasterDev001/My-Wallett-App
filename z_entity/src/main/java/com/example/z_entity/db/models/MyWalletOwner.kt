package com.example.z_entity.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.a_common.data.WalletOwnerData

@Entity(tableName = "walletOwner")
data class MyWalletOwner(
    @PrimaryKey
    var id: String,
    var walletId: String,
    var currencyId: String,
    var currencyBalance: Double,
    var rate: Double
)

fun WalletOwnerData.toMyWalletOwner() =
    MyWalletOwner(id, walletId, currencyId, currencyBalance, rate)

fun MyWalletOwner.toWalletOwnerData() =
    WalletOwnerData(id, walletId, currencyId, currencyBalance, rate)
