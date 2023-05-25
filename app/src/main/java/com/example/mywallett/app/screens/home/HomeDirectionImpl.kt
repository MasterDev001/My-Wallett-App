package com.example.mywallett.app.screens.home

import com.example.mywallett.app.screens.currencies.CurrencyScreen
import com.example.mywallett.app.screens.wallets.WalletsScreen
import com.example.mywallett.navigation.AppNavigator
import com.example.presenter.home.HomeDirection
import javax.inject.Inject

class HomeDirectionImpl @Inject constructor(private val navigator: AppNavigator) : HomeDirection {

    override suspend fun navigateToKirim() {
        TODO("Not yet implemented")
    }

    override suspend fun navigateToChiqim() {
        TODO("Not yet implemented")
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