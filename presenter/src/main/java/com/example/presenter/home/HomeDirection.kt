package com.example.presenter.home

import com.example.a_common.data.CurrencyData
import com.example.a_common.data.PersonData
import com.example.a_common.data.WalletData

interface HomeDirection {

    suspend fun navigateToInCome()
    suspend fun navigateToOutCome()
    suspend fun navigateToBorrow(
        persons: List<PersonData>,
        currencies: List<CurrencyData>,
        wallets: List<WalletData>
    )

    suspend fun navigateToLend(
        persons: List<PersonData>,
        currencies: List<CurrencyData>,
        wallets: List<WalletData>
    )

    suspend fun navigateToConvert(
        currencies: List<CurrencyData>,
        wallets: List<WalletData>
    )

    suspend fun navigateToPersons()
    suspend fun navigateToWallets()
    suspend fun navigateToHistory()
    suspend fun navigateToCurrencies()
    suspend fun navigateToSettings()
}