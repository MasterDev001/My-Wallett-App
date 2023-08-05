package com.example.z_entity.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.z_entity.db.models.MyPersonCurrency
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonCurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPersonCurrency(personCurrency: MyPersonCurrency): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPersonCurrencyList(personCurrencyList: List<MyPersonCurrency>): List<Long>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updatePersonCurrency(personCurrency: MyPersonCurrency): Int

    @Query("SELECT * FROM personCurrency WHERE personId = :personId AND currencyId = :currencyId limit 1")
    fun getPersonCurrency(personId: String, currencyId: String): MyPersonCurrency

    @Query("DELETE FROM personCurrency WHERE id=:id ")
    suspend fun deletePersonCurrency(id: String): Int

    @Query("SELECT * FROM personCurrency WHERE personId = :personId")
    fun getPersonCurriesByPersonId(personId: String): Flow<List<MyPersonCurrency>>

    @Query("SELECT EXISTS(SELECT 1 FROM personCurrency WHERE personId = :personId)")
    fun isPersonCurrencyExists(personId: String): Boolean

    @Query("SELECT EXISTS(SELECT 1 FROM personCurrency WHERE personId = :personId AND currencyId = :currencyId)")
    fun isPersonCurrenciesCurrencyExists(personId: String, currencyId: String): Boolean

    @Query("SELECT * FROM personCurrency WHERE currencyBalance > 0")
    fun getAllDebtors(): Flow<List<MyPersonCurrency>>

    @Query("SELECT * FROM personCurrency WHERE currencyBalance < 0")
    fun getAllLenders(): Flow<List<MyPersonCurrency>>

    @Query("SELECT * FROM personCurrency WHERE uploaded=0")
    fun getNotUploaded(): Flow<List<MyPersonCurrency>>

    @Query("SELECT EXISTS(SELECT 1 FROM personCurrency WHERE uploaded =0)")
    fun isNeedUpdate(): Boolean
}