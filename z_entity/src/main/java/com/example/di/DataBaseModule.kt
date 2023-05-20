package com.example.di

import android.content.Context
import androidx.room.Room
import com.example.db.MyDatabase
import com.example.db.daos.CurrencyDao
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
//            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideCurrency(db:MyDatabase):CurrencyDao=db.currencyDao

}