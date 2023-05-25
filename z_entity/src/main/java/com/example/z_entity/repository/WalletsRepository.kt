package com.example.z_entity.repository

import com.example.z_entity.db.entity.MyWallet
import kotlinx.coroutines.flow.Flow

interface WalletsRepository {
    suspend fun addWallet(wallet: MyWallet): Long
    suspend fun updateWallet(wallet: MyWallet): Int
    suspend fun deleteWallet(wallet: MyWallet): Int
    suspend fun getWallet(name: String): Flow<MyWallet>
    suspend fun getAllWallets(): Flow<List<MyWallet>>
}