package com.example.z_entity.repository

import com.example.z_entity.db.models.MyPersonCurrency
import kotlinx.coroutines.flow.Flow

interface PersonCurrencyRepository {

    suspend fun addPersonCurrency(personCurrency: MyPersonCurrency): Long
    suspend fun updatePersonCurrency(personCurrency: MyPersonCurrency): Int
    suspend fun deletePersonCurrency(id: String): Int
    suspend fun getPersonCurriesByPersonId(personId: String): Flow<List<MyPersonCurrency>>
    fun isPersonCurrencyExists(personId: String): Boolean
    fun isPersonCurrenciesCurrencyExists(personId: String, currencyId: String): Boolean
    fun getPersonCurrency(personId: String, currencyId: String): MyPersonCurrency
    suspend fun getAllDebtors(): Flow<List<MyPersonCurrency>>
    suspend fun getAllLenders(): Flow<List<MyPersonCurrency>>
}