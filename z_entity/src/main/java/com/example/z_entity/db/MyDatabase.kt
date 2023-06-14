package com.example.z_entity.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.z_entity.db.daos.CurrencyDao
import com.example.z_entity.db.daos.TransactionDao
import com.example.z_entity.db.daos.WalletsDao
import com.example.z_entity.db.entity.MyCurrency
import com.example.z_entity.db.entity.MyTransaction
import com.example.z_entity.db.entity.MyWallet
import com.example.z_entity.db.entity.MyWalletOwner

@Database(entities = [MyCurrency::class, MyWallet::class,MyTransaction::class,MyWalletOwner::class], version = 7 )
@TypeConverters(value =[Converters::class])
abstract class MyDatabase : RoomDatabase() {

    abstract val currencyDao: CurrencyDao
    abstract val walletsDao: WalletsDao
    abstract val transactionDao:TransactionDao

    companion object {
        const val DATABASE_NAME = "my_wallet"
    }

}