package com.example.mywallett.app.screens.home

import com.example.mywallett.app.screens.currencies.CurrencyScreen
import com.example.mywallett.app.screens.income.InComeScreen
import com.example.mywallett.app.screens.outcome.OutComeScreen
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

    override suspend fun navigateToQarzOlish() {
        TODO("Not yet implemented")
    }

    override suspend fun navigateToQarzBerish() {
        TODO("Not yet implemented")
    }

    override suspend fun navigateToHaqdorlar() {
        TODO("Not yet implemented")
    }

    override suspend fun navigateToWallets() {
        navigator.navigateTo(WalletsScreen())
    }

    override suspend fun navigateToHistory() {
        TODO("Not yet implemented")
    }

    override suspend fun navigateToCurrencies() {
        navigator.navigateTo(CurrencyScreen())
    }


}