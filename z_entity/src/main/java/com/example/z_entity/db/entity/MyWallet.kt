package com.example.z_entity.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.a_common.data.WalletData
import com.example.z_entity.db.remote_models.WalletRemote

@Entity(tableName = "wallets")
data class MyWallet(
    @PrimaryKey val id: String, val name: String, val date: Long, val uploaded: Boolean = false
) {
    fun toWalletRemote() = WalletRemote(id, name, date)
}

fun MyWallet.toWalletData() = WalletData(id, name, date)
fun WalletData.toMyWallet() = MyWallet(id, name, date)