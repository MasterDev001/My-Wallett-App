package com.example.z_entity.repository

import com.example.z_entity.db.entity.MyCurrency
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {
    suspend fun addCurrency(currency: MyCurrency): Long
    suspend fun updateCurrency(currency: MyCurrency): Int
    suspend fun deleteCurrency(currency: MyCurrency): Int
    suspend fun getTotalBalance(): Double
    fun getCurrency(id: String): MyCurrency
    fun isCurrencyExist(name: String): Boolean
    suspend fun getAllCurrencies(): Flow<List<MyCurrency>>
}