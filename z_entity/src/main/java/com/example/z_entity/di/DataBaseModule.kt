package com.example.z_entity.di

import android.content.Context
import androidx.room.Room
import com.example.z_entity.db.MyDatabase
import com.example.z_entity.db.daos.CurrencyDao
import com.example.z_entity.db.daos.HistoryDao
import com.example.z_entity.db.daos.PersonCurrencyDao
import com.example.z_entity.db.daos.PersonsDao
import com.example.z_entity.db.daos.TransactionDao
import com.example.z_entity.db.daos.WalletsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MyDatabase =
        Room.databaseBuilder(context, MyDatabase::class.java, MyDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration().allowMainThreadQueries()
            .build()

    @Provides
    @Singleton
    fun provideCurrency(db: MyDatabase): CurrencyDao = db.currencyDao

    @[Provides Singleton]
    fun provideWallets(db: MyDatabase): WalletsDao = db.walletsDao

    @[Provides Singleton]
    fun provideTransaction(db: MyDatabase): TransactionDao = db.transactionDao

    @[Provides Singleton]
    fun providePersons(db: MyDatabase): PersonsDao = db.personsDao

    @[Provides Singleton]
    fun providePersonCurrency(db: MyDatabase): PersonCurrencyDao = db.personCurrencyDao

    @[Provides Singleton]
    fun provideHistory(db: MyDatabase): HistoryDao = db.historyDao
}