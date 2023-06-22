package com.example.z_entity.repository

import com.example.z_entity.db.entity.MyWallet
import com.example.z_entity.db.models.MyWalletOwnerList
import kotlinx.coroutines.flow.Flow

interface WalletsRepository {
    suspend fun addWallet(wallet: MyWallet): Long
    suspend fun updateWallet(wallet: MyWallet): Int
    suspend fun deleteWallet(wallet: MyWallet): Int
    suspend fun getWalletOwnerList(walletId: String): Flow<MyWalletOwnerList>
     fun isCurrencyIdExistsInWallet(walletId: String, currencyId: String): Boolean
    fun isWalletNameExists(name: String): Boolean
    suspend fun getAllWallets(): Flow<List<MyWallet>>
}