package com.example.z_entity.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.z_entity.db.daos.CurrencyDao
import com.example.z_entity.db.daos.HistoryDao
import com.example.z_entity.db.daos.PersonCurrencyDao
import com.example.z_entity.db.daos.PersonsDao
import com.example.z_entity.db.daos.TransactionDao
import com.example.z_entity.db.daos.WalletsDao
import com.example.z_entity.db.entity.MyCurrency
import com.example.z_entity.db.entity.MyPerson
import com.example.z_entity.db.entity.MyTransaction
import com.example.z_entity.db.entity.MyWallet
import com.example.z_entity.db.models.MyPersonCurrency

@Database(
    entities = [
        MyCurrency::class,
        MyWallet::class,
        MyTransaction::class,
//        MyWalletOwner::class,
        MyPerson::class,
        MyPersonCurrency::class,
    ],
    version = 15
)
@TypeConverters(value = [Converters::class])
abstract class MyDatabase : RoomDatabase() {

    abstract val currencyDao: CurrencyDao
    abstract val walletsDao: WalletsDao
    abstract val transactionDao: TransactionDao
    abstract val personsDao: PersonsDao
    abstract val personCurrencyDao: PersonCurrencyDao
    abstract val historyDao: HistoryDao

    companion object {
        const val DATABASE_NAME = "my_wallet"
    }

}