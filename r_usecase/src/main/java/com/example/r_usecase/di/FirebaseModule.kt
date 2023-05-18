package com.example.r_usecase.di

import com.example.repository.AuthRepository
import com.example.r_usecase.repositoryimpl.AuthRepositoryImpl
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object FirebaseModule {

    @Provides
    @Singleton
    fun authRepositoryImpl(): AuthRepository = AuthRepositoryImpl(
            auth = Firebase.auth,
            fireStore = Firebase.firestore,
        )

    @Provides
    @Singleton
    fun firebaseDatabase():DatabaseReference=FirebaseDatabase.getInstance().reference

}