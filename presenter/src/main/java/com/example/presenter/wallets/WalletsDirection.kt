package com.example.presenter.wallets

import com.example.a_common.data.WalletData

interface WalletsDirection {

    suspend fun back()
    suspend fun navigateToWalletHistory(walletData: WalletData)
}