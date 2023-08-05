package com.example.z_entity.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.z_entity.db.entity.MyTransaction
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(transaction: MyTransaction): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(transaction: MyTransaction): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTransactions(transactions: List<MyTransaction>): List<Long>

    @Query("DELETE FROM transactions WHERE date=:date")
    suspend fun delete(date: String): Int

    @Query("DELETE FROM transactions")
    fun clear()

    @Query("SELECT * FROM transactions WHERE date=:date LIMIT 1")
    fun getById(date: String): MyTransaction

    @Query("SELECT * FROM transactions order by date desc")
    fun getAll(): Flow<List<MyTransaction>>

    @Query("SELECT COUNT(*) FROM transactions")
    fun getCount(): Int

    @Query("SELECT MAX(date) FROM transactions")
    fun getLastUpdateTime(): Long

    @Query("SELECT * FROM transactions WHERE uploaded=0")
    fun getNotUploaded(): Flow<List<MyTransaction>>

    @Query("SELECT EXISTS(SELECT 1 FROM transactions WHERE uploaded =0)")
    fun isNeedUpdate(): Boolean

}