package com.example.mywallett.app.screens.wallets.walletHistory

import com.example.mywallett.navigation.AppNavigator
import com.example.presenter.wallets.walletHistory.WalletHistoryDirection
import javax.inject.Inject

class WalletHistoryDirectionImpl @Inject constructor(private val navigator: AppNavigator) :
    WalletHistoryDirection {

    override suspend fun back() {
        navigator.back()
    }

}