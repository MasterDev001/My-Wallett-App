package com.example.mywallett.app.screens.wallets

import com.example.a_common.data.WalletData
import com.example.mywallett.app.screens.wallets.walletHistory.WalletHistoryScreen
import com.example.mywallett.navigation.AppNavigator
import com.example.presenter.wallets.WalletsDirection
import javax.inject.Inject

class WalletsDirectionImpl @Inject constructor(
    private val navigator: AppNavigator
) : WalletsDirection {

    override suspend fun back() {
        navigator.back()
    }

    override suspend fun navigateToWalletHistory(walletData: WalletData) {
        navigator.navigateTo(WalletHistoryScreen(walletData))
    }
}