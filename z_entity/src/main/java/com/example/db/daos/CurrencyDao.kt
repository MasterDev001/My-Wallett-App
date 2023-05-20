package com.example.db.daos

import MyCurrency
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCurrency(currency: MyCurrency): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCurrency(currency: MyCurrency): Int

    @Query("DELETE FROM currencies WHERE id=:id")
    suspend fun deleteCurrency(id: String): Int

    @Query("SELECT * FROM currencies WHERE name=:name")
    suspend fun getCurrency(name: String): Flow<MyCurrency>

    @Query("SELECT * FROM currencies")
    suspend fun getAllCurrencies(): Flow<List<MyCurrency>>
}