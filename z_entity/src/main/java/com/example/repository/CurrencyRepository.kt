package com.example.repository

import MyCurrency
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {
    suspend fun addCurrency(currency: MyCurrency): Long
    suspend fun updateCurrency(currency: MyCurrency): Int
    suspend fun deleteCurrency(currency: MyCurrency): Int
    suspend fun getCurrency(name: String): Flow<MyCurrency>
    suspend fun getAllCurrencies(): Flow<List<MyCurrency>>
}