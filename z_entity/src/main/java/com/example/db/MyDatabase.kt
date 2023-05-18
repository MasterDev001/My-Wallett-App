package com.example.db

import Currency
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.db.daos.CurrencyDao

@Database(entities = [Currency::class], version = 1)
abstract class MyDatabase : RoomDatabase() {

    abstract val currencyDao: CurrencyDao

    companion object {
        const val DATABASE_NAME = "my_wallet"
    }

}