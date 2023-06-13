package com.example.r_usecase.di

import com.example.r_usecase.usecases.currencyUseCase.AddCurrencyUseCase
import com.example.r_usecase.usecases.currencyUseCase.CurrencyUseCase
import com.example.r_usecase.usecases.currencyUseCase.DeleteCurrencyUseCase
import com.example.r_usecase.usecases.currencyUseCase.GetAllCurrenciesUseC
import com.example.r_usecase.usecases.currencyUseCase.GetCurrencyUseC
import com.example.r_usecase.usecases.currencyUseCase.UpdateCurrencyUseCase
import com.example.r_usecase.usecases.transactionUseCase.AddTransactionUseC
import com.example.r_usecase.usecases.transactionUseCase.DeleteTransactionUseC
import com.example.r_usecase.usecases.transactionUseCase.GetAllTransactionsUseC
import com.example.r_usecase.usecases.transactionUseCase.TransactionUseCase
import com.example.r_usecase.usecases.walletsUseCase.AddWalletUseC
import com.example.r_usecase.usecases.walletsUseCase.DeleteWalletUseC
import com.example.r_usecase.usecases.walletsUseCase.GetAllWalletsUseC
import com.example.r_usecase.usecases.walletsUseCase.GetWalletOwnerListUseC
import com.example.r_usecase.usecases.walletsUseCase.GetWalletUseC
import com.example.r_usecase.usecases.walletsUseCase.IsCurrencyIdExistsInWalletUseC
import com.example.r_usecase.usecases.walletsUseCase.UpdateWalletUseC
import com.example.r_usecase.usecases.walletsUseCase.WalletsUseCase
import com.example.z_entity.repository.CurrencyRepository
import com.example.z_entity.repository.TransactionRepository
import com.example.z_entity.repository.WalletsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object UseCaseModule {

    @[Provides Singleton]
    fun provideUseCase(repository: CurrencyRepository): CurrencyUseCase {
        return CurrencyUseCase(
            addCurrency = AddCurrencyUseCase(repository),
            deleteCurrency = DeleteCurrencyUseCase(repository),
            updateCurrency = UpdateCurrencyUseCase(repository),
            getCurrency = GetCurrencyUseC(repository),
            getAllCurrencies = GetAllCurrenciesUseC(repository)
        )
    }

    @[Provides Singleton]
    fun provideWalletsUseCase(walletsRepository: WalletsRepository): WalletsUseCase {
        return WalletsUseCase(
            addWalletUseC = AddWalletUseC(walletsRepository),
            updateWalletUseC = UpdateWalletUseC(walletsRepository),
            deleteWalletUseC = DeleteWalletUseC(walletsRepository),
            getWalletUseC = GetWalletUseC(walletsRepository),
            getAllWalletsUseC = GetAllWalletsUseC(walletsRepository),
            getWalletOwnerListUseC = GetWalletOwnerListUseC(walletsRepository),
            isCurrencyIdExistsInWalletUseC = IsCurrencyIdExistsInWalletUseC(walletsRepository)
        )
    }

    @[Provides Singleton]
    fun provideTransactionUseCase(
        transactionRepository: TransactionRepository,
        walletsUseCase: WalletsUseCase
    ): TransactionUseCase {
        return TransactionUseCase(
            addTransaction = AddTransactionUseC(transactionRepository, walletsUseCase),
            deleteTransaction = DeleteTransactionUseC(transactionRepository),
            getAllTransactions = GetAllTransactionsUseC(transactionRepository)
        )
    }

}