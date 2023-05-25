package com.example.r_usecase.di

import com.example.r_usecase.usecases.currencyUseCase.AddCurrencyUseCase
import com.example.r_usecase.usecases.currencyUseCase.CurrencyUseCase
import com.example.r_usecase.usecases.currencyUseCase.DeleteCurrencyUseCase
import com.example.r_usecase.usecases.currencyUseCase.GetAllCurrenciesUseC
import com.example.r_usecase.usecases.currencyUseCase.GetCurrencyUseC
import com.example.r_usecase.usecases.currencyUseCase.UpdateCurrencyUseCase
import com.example.z_entity.repository.CurrencyRepository
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