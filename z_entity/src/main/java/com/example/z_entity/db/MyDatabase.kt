package com.example.z_entity.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.z_entity.db.daos.CurrencyDao
import com.example.z_entity.db.daos.WalletsDao
import com.example.z_entity.db.entity.MyCurrency
import com.example.z_entity.db.entity.MyWallet

@Database(entities = [MyCurrency::class,MyWallet::class], version = 3)
abstract class MyDatabase : RoomDatabase() {

    abstract val currencyDao: CurrencyDao
    abstract val walletsDao: WalletsDao

    companion object {
        const val DATABASE_NAME = "my_wallet"
    }

}