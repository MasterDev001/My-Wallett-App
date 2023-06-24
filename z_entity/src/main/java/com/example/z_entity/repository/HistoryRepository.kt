package com.example.z_entity.repository

import com.example.z_entity.db.models.MyHistory
import kotlinx.coroutines.flow.Flow

interface HistoryRepository {

    fun getByOwnerId(ownerId: String): Flow<List<MyHistory>>
    fun getForHome(count: Int): Flow<List<MyHistory>>
    fun getHistory(): Flow<List<MyHistory>>
    fun getHistory(limit: Int, page: Int): List<MyHistory>
    fun getHistoryCount(): Flow<Int>
}