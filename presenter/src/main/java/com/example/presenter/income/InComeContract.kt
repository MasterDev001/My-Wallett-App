package com.example.presenter.income

import com.example.a_common.data.CurrencyData
import com.example.a_common.data.WalletData

class InComeContract {

    sealed interface Intent {
        object OpenHome : Intent
        class AddWallet(val walletData: WalletData) : Intent
        class IncomeMoney(
            val amount: Double,
            val comment: String,
            val currencyData: CurrencyData,
            val wallet: WalletData
        ) : Intent
        class UpdateWallet(val walletData: WalletData) : Intent
    }

    interface UiState {
        object Default : UiState
        object Loading : UiState
        class Error(val error: String) : UiState
    }
}