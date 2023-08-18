package com.example.z_entity.db.daos


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.z_entity.db.entity.MyCurrency
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCurrency(currency: MyCurrency): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCurrencyList(currencyList: List<MyCurrency>): List<Long>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCurrency(currency: MyCurrency): Int

    @Query("DELETE FROM currencies WHERE id=:id")
    suspend fun deleteCurrency(id: String): Int

    @Query("SELECT * FROM currencies WHERE id=:id")
     fun getCurrency(id: String): MyCurrency

    @Query("SELECT * FROM currencies")
     fun getAllCurrencies(): Flow<List<MyCurrency>>

    @Query("SELECT EXISTS(SELECT 1 FROM currencies WHERE LOWER(name) = LOWER(:name))")
    fun isCurrencyExists(name: String): Boolean

    @Query("SELECT * FROM currencies WHERE uploaded=0")
    fun getNotUploaded(): Flow<List<MyCurrency>>

    @Query("SELECT EXISTS(SELECT 1 FROM currencies WHERE uploaded =0)")
    fun isNeedUpdate(): Boolean

    @Query("SELECT SUM(balance/rate) FROM currencies")
    fun getTotalBalance():Double
}