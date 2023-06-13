package com.example.z_entity.repository

import com.example.z_entity.db.entity.MyTransaction
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {

    suspend fun add(transaction: MyTransaction): Long
    suspend fun delete(transaction: MyTransaction): Int
    suspend fun getAll(): Flow<List<MyTransaction>>
}