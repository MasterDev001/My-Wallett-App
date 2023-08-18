package com.example.presenter.convert

import com.example.a_common.data.CurrencyData
import com.example.a_common.data.WalletData
import com.example.a_common.data.WalletOwnerData

class ConvertContract {

    sealed interface Intent {
        object OpenHome : Intent
        class ConvertMoney(
            val amount: String,
            val fromWalletOwner: WalletOwnerData,
            val fromWallet: WalletData,
            val toWallet: WalletData,
            val fromCurrency:CurrencyData,
            val toCurrency: CurrencyData,
            val rate: String = "1.0"
        ) : Intent
    }

    interface UiState {
        object Default : UiState
        object Loading : UiState
        class Error(val error: String) : UiState
    }
}