package com.example.presenter.outcome

import com.example.a_common.data.WalletData

interface OutComeDirection {

    suspend fun navigateToOutComeCurrencies(wallet: WalletData)
    suspend fun back()
}