package com.example.mywallett.app.screens.outcome

import com.example.a_common.data.WalletData
import com.example.mywallett.app.screens.outcome.outcome_currencies.OutComeCurrenciesScreen
import com.example.mywallett.navigation.AppNavigator
import com.example.presenter.outcome.OutComeDirection
import javax.inject.Inject

class OutComeDirectionImpl @Inject constructor(private val navigator: AppNavigator) :
    OutComeDirection {

    override suspend fun navigateToOutComeCurrencies(wallet: WalletData) {
        navigator.navigateTo(OutComeCurrenciesScreen(wallet))
    }

    override suspend fun back() {
        navigator.back()
    }
}