package com.example.r_usecase.di

import com.example.r_usecase.usecases.currencyUseCase.AddCurrencyUseCase
import com.example.r_usecase.usecases.currencyUseCase.CurrencyUseCase
import com.example.r_usecase.usecases.currencyUseCase.DeleteCurrencyUseCase
import com.example.r_usecase.usecases.currencyUseCase.GetAllCurrenciesUseC
import com.example.r_usecase.usecases.currencyUseCase.GetCurrencyUseC
import com.example.r_usecase.usecases.currencyUseCase.IsCurrencyExistUseC
import com.example.r_usecase.usecases.currencyUseCase.UpdateCurrencyUseCase
import com.example.r_usecase.usecases.personCurrencyUseCase.AddPersonCurrencyUseC
import com.example.r_usecase.usecases.personCurrencyUseCase.DeletePersonCurrencyUseC
import com.example.r_usecase.usecases.personCurrencyUseCase.GetAllDebtors
import com.example.r_usecase.usecases.personCurrencyUseCase.GetAllLenders
import com.example.r_usecase.usecases.personCurrencyUseCase.GetPersonCurrencyUseC
import com.example.r_usecase.usecases.personCurrencyUseCase.GetPersonCurriesByPersonIdUseC
import com.example.r_usecase.usecases.personCurrencyUseCase.IsPersonCurrenciesCurrencyExistUseC
import com.example.r_usecase.usecases.personCurrencyUseCase.IsPersonCurrencyExistUseC
import com.example.r_usecase.usecases.personCurrencyUseCase.PersonCurrencyUseCase
import com.example.r_usecase.usecases.personCurrencyUseCase.UpdatePersonCurrencyUseC
import com.example.r_usecase.usecases.personUseCase.AddPersonUseC
import com.example.r_usecase.usecases.personUseCase.DeletePersonUseC
import com.example.r_usecase.usecases.personUseCase.GetAllPersonsUseC
import com.example.r_usecase.usecases.personUseCase.GetPersonUseC
import com.example.r_usecase.usecases.personUseCase.IsPersonExistUseC
import com.example.r_usecase.usecases.personUseCase.PersonsUseCase
import com.example.r_usecase.usecases.personUseCase.UpdatePersonUseC
import com.example.r_usecase.usecases.transactionUseCase.AddTransactionUseC
import com.example.r_usecase.usecases.transactionUseCase.BorrowUseCase
import com.example.r_usecase.usecases.transactionUseCase.ConvertUseCase
import com.example.r_usecase.usecases.transactionUseCase.DeleteTransactionUseC
import com.example.r_usecase.usecases.transactionUseCase.GetAllTransactionsUseC
import com.example.r_usecase.usecases.transactionUseCase.LendUseCase
import com.example.r_usecase.usecases.transactionUseCase.TransactionUseCase
import com.example.r_usecase.usecases.walletsUseCase.AddWalletUseC
import com.example.r_usecase.usecases.walletsUseCase.DeleteWalletUseC
import com.example.r_usecase.usecases.walletsUseCase.GetAllWalletsUseC
import com.example.r_usecase.usecases.walletsUseCase.GetWalletOwnerListUseC
import com.example.r_usecase.usecases.walletsUseCase.InComeUseCase
import com.example.r_usecase.usecases.walletsUseCase.IsCurrencyIdExistsInWalletUseC
import com.example.r_usecase.usecases.walletsUseCase.IsWalletExist
import com.example.r_usecase.usecases.walletsUseCase.OutComeUseCase
import com.example.r_usecase.usecases.walletsUseCase.UpdateWalletUseC
import com.example.r_usecase.usecases.walletsUseCase.WalletsUseCase
import com.example.z_entity.repository.CurrencyRepository
import com.example.z_entity.repository.PersonCurrencyRepository
import com.example.z_entity.repository.PersonsRepository
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
            getAllCurrencies = GetAllCurrenciesUseC(repository),
            isCurrencyExistUseC = IsCurrencyExistUseC(repository)
        )
    }

    @[Provides Singleton]
    fun provideWalletsUseCase(walletsRepository: WalletsRepository): WalletsUseCase {
        return WalletsUseCase(
            addWalletUseC = AddWalletUseC(walletsRepository),
            updateWalletUseC = UpdateWalletUseC(walletsRepository),
            deleteWalletUseC = DeleteWalletUseC(walletsRepository),
            isWalletExist = IsWalletExist(walletsRepository),
            getAllWalletsUseC = GetAllWalletsUseC(walletsRepository),
            getWalletOwnerListUseC = GetWalletOwnerListUseC(walletsRepository),
            isCurrencyIdExistsInWalletUseC = IsCurrencyIdExistsInWalletUseC(walletsRepository),
            outComeUseCase = OutComeUseCase(walletsRepository),
            inComeUseCase = InComeUseCase(walletsRepository)
        )
    }

    @[Provides Singleton]
    fun provideTransactionUseCase(
        transactionRepository: TransactionRepository,
        walletsUseCase: WalletsUseCase,
        personCurrencyRepository: PersonCurrencyRepository,
    ): TransactionUseCase {
        return TransactionUseCase(
            addTransaction = AddTransactionUseC(transactionRepository, walletsUseCase),
            deleteTransaction = DeleteTransactionUseC(transactionRepository),
            getAllTransactions = GetAllTransactionsUseC(transactionRepository),
            borrowUseCase = BorrowUseCase(
                personCurrencyRepository,
                transactionRepository,
                walletsUseCase
            ),
            lendUseCase = LendUseCase(
                personCurrencyRepository,
                transactionRepository,
                walletsUseCase
            ),
            convertUseCase = ConvertUseCase(transactionRepository,walletsUseCase)
        )
    }

    @[Provides Singleton]
    fun providePersonsUseCase(personsRepository: PersonsRepository): PersonsUseCase {
        return PersonsUseCase(
            addPersonUseC = AddPersonUseC(personsRepository),
            deletePersonUseC = DeletePersonUseC(personsRepository),
            getAllPersonsUseC = GetAllPersonsUseC(personsRepository),
            getPersonUseC = GetPersonUseC(personsRepository),
            updatePersonUseC = UpdatePersonUseC(personsRepository),
            isPersonExistUseC = IsPersonExistUseC(personsRepository),
        )
    }

    @[Provides Singleton]
    fun providePersonCurrencyUseCase(personCurrencyRepository: PersonCurrencyRepository): PersonCurrencyUseCase {
        return PersonCurrencyUseCase(
            getPersonCurriesByPersonIdUseC = GetPersonCurriesByPersonIdUseC(personCurrencyRepository),
            getAllDebtors = GetAllDebtors(personCurrencyRepository),
            getAllLenders = GetAllLenders(personCurrencyRepository),
            isPersonCurrencyExistUseC = IsPersonCurrencyExistUseC(personCurrencyRepository),
            addPersonCurrencyUseC = AddPersonCurrencyUseC(personCurrencyRepository),
            updatePersonCurrencyUseC = UpdatePersonCurrencyUseC(personCurrencyRepository),
            deletePersonCurrencyUseC = DeletePersonCurrencyUseC(personCurrencyRepository),
            isPersonCurrenciesCurrencyExistUseC = IsPersonCurrenciesCurrencyExistUseC(
                personCurrencyRepository
            ),
            getPersonCurrencyUseC = GetPersonCurrencyUseC(personCurrencyRepository)
        )
    }
}