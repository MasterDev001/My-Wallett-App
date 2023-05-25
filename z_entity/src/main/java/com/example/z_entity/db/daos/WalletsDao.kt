package com.example.z_entity.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.z_entity.db.entity.MyWallet
import kotlinx.coroutines.flow.Flow

@Dao
interface WalletsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addWallet(wallet: MyWallet): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateWallet(wallet: MyWallet): Int

    @Query("DELETE FROM wallets WHERE id=:id")
    suspend fun deleteWallet(id: String): Int

    @Query("SELECT * FROM wallets WHERE name=:name")
    fun getWallet(name: String): Flow<MyWallet>

    @Query("SELECT * FROM wallets")
    fun getAllWallets(): Flow<List<MyWallet>>
}