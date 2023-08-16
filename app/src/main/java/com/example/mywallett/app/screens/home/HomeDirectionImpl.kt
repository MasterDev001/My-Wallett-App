package com.example.mywallett.app.screens.home

import com.example.a_common.data.CurrencyData
import com.example.a_common.data.PersonData
import com.example.a_common.data.WalletData
import com.example.mywallett.app.screens.borrow.BorrowScreen
import com.example.mywallett.app.screens.convert.ConvertScreen
import com.example.mywallett.app.screens.currencies.CurrencyScreen
import com.example.mywallett.app.screens.history.HistoryScreen
import com.example.mywallett.app.screens.income.InComeScreen
import com.example.mywallett.app.screens.lend.LendScreen
import com.example.mywallett.app.screens.outcome.OutComeScreen
import com.example.mywallett.app.screens.persons.PersonsScreen
import com.example.mywallett.app.screens.share.ShareScreen
import com.example.mywallett.app.screens.wallets.WalletsScreen
import com.example.mywallett.navigation.AppNavigator
import com.example.presenter.home.HomeDirection
import javax.inject.Inject

class HomeDirectionImpl @Inject constructor(private val navigator: AppNavigator) : HomeDirection {

    override suspend fun navigateToInCome() {
        navigator.navigateTo(InComeScreen())
    }

    override suspend fun navigateToOutCome() {
        navigator.navigateTo(OutComeScreen())
    }

    override suspend fun navigateToBorrow(
        persons: List<PersonData>,
        currencies: List<CurrencyData>,
        wallets: List<WalletData>
    ) {
        navigator.navigateTo(BorrowScreen(persons, currencies, wallets))
    }

    override suspend fun navigateToLend(
        persons: List<PersonData>,
        currencies: List<CurrencyData>,
        wallets: List<WalletData>
    ) {
        navigator.navigateTo(LendScreen(persons, currencies, wallets))
    }

    override suspend fun navigateToConvert(
        currencies: List<CurrencyData>,
        wallets: List<WalletData>
    ) {
        navigator.navigateTo(ConvertScreen( currencies, wallets))
    }

    override suspend fun navigateToShare() {
        navigator.navigateTo(ShareScreen())
    }

    override suspend fun navigateToPersons() {
        navigator.navigateTo(PersonsScreen())
    }

    override suspend fun navigateToWallets() {
        navigator.navigateTo(WalletsScreen())
    }

    override suspend fun navigateToHistory() {
        navigator.navigateTo(HistoryScreen())
    }

    override suspend fun navigateToCurrencies() {
        navigator.navigateTo(CurrencyScreen())
    }

    override suspend fun navigateToSettings() {
        navigator.navigateTo(CurrencyScreen())
    }
}