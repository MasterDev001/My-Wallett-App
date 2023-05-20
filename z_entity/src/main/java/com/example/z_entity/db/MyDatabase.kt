package com.example.z_entity.db

import com.example.z_entity.db.entity.MyCurrency
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.z_entity.db.daos.CurrencyDao

@Database(entities = [MyCurrency::class], version = 1)
abstract class MyDatabase : RoomDatabase() {

    abstract val currencyDao: CurrencyDao

    companion object {
        const val DATABASE_NAME = "my_wallet"
    }

}