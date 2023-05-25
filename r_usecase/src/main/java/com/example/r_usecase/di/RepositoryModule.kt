package com.example.r_usecase.di

import com.example.r_usecase.repositoryimpl.AuthRepositoryImpl
import com.example.r_usecase.repositoryimpl.CurrencyRepositoryImpl
import com.example.r_usecase.repositoryimpl.WalletsRepositoryImpl
import com.example.z_entity.db.daos.CurrencyDao
import com.example.z_entity.db.daos.WalletsDao
import com.example.z_entity.repository.AuthRepository
import com.example.z_entity.repository.CurrencyRepository
import com.example.z_entity.repository.WalletsRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object RepositoryModule {

    @Provides
    @Singleton
    fun firebaseDatabase(): DatabaseReference = FirebaseDatabase.getInstance().reference

    @[Provides Singleton]
    fun firebaseFireStore(): FirebaseFirestore = Firebase.firestore

    @Provides
    @Singleton
    fun authRepositoryImpl(fireStore: FirebaseFirestore): AuthRepository = AuthRepositoryImpl(
        auth = Firebase.auth,
        fireStore = fireStore
    )

    @Provides
    @Singleton
    fun currencyRepositoryImpl(
        currencyDao: CurrencyDao,
        fireStore: FirebaseFirestore,
        authRepository: AuthRepository
    ): CurrencyRepository = CurrencyRepositoryImpl(
        local = currencyDao,
        fireStore = fireStore,
        authRepository = authRepository
    )

    @[Provides Singleton]
    fun walletsRepositoryImpl(
        walletsDao: WalletsDao,
        fireStore: FirebaseFirestore,
        authRepository: AuthRepository
    ): WalletsRepository = WalletsRepositoryImpl(
        local = walletsDao,
        fireStore = fireStore,
        authRepository = authRepository,
    )
}