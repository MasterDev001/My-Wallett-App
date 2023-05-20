package com.example.r_usecase.di

import com.example.z_entity.db.daos.CurrencyDao
import com.example.z_entity.repository.AuthRepository
import com.example.r_usecase.repositoryimpl.AuthRepositoryImpl
import com.example.r_usecase.repositoryimpl.CurrencyRepositoryImpl
import com.example.r_usecase.usecases.currencyUseCase.AddCurrencyUseCase
import com.example.r_usecase.usecases.currencyUseCase.CurrencyUseCase
import com.example.r_usecase.usecases.currencyUseCase.DeleteCurrencyUseCase
import com.example.r_usecase.usecases.currencyUseCase.GetAllCurrenciesUseC
import com.example.r_usecase.usecases.currencyUseCase.GetCurrencyUseC
import com.example.r_usecase.usecases.currencyUseCase.UpdateCurrencyUseCase
import com.example.z_entity.repository.CurrencyRepository
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
internal object UseCaseModule {

    @Provides
    @Singleton
    fun authRepositoryImpl(): AuthRepository = AuthRepositoryImpl(
        auth = Firebase.auth,
        fireStore = Firebase.firestore
    )

    @Provides
    @Singleton
    fun currencyRepositoryImpl(
        currencyDao: CurrencyDao,
        authRepository: AuthRepository
    ): CurrencyRepository =
        CurrencyRepositoryImpl(
            local = currencyDao,
            fireStore = Firebase.firestore,
            authRepository = authRepository
        )

    @Provides
    @Singleton
    fun firebaseDatabase(): DatabaseReference = FirebaseDatabase.getInstance().reference

    @Provides
    @Singleton
    fun provideUseCase(repository: CurrencyRepository): CurrencyUseCase {
        return CurrencyUseCase(
            addCurrency = AddCurrencyUseCase(repository),
            deleteCurrency = DeleteCurrencyUseCase(repository),
            updateCurrency = UpdateCurrencyUseCase(repository),
            getCurrency = GetCurrencyUseC(repository),
            getAllCurrencies = GetAllCurrenciesUseC(repository)
        )
    }

}