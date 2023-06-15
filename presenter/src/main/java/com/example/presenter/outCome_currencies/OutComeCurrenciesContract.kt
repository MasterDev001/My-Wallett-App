package com.example.presenter.outCome_currencies

import com.example.a_common.data.CurrencyData
import com.example.a_common.data.WalletData
import com.example.a_common.data.WalletOwnerData

class OutComeCurrenciesContract {

    sealed interface Intent {
        object OpenOutCome : Intent
        class OutComeMoney(
            val amount: Double,
            val comment: String,
            val currencyData: CurrencyData,
            val wallet: WalletData,
            val currentWalletOwner: WalletOwnerData,
            val currentWalletOwnerIndex: Int
        ) : Intent
    }

    interface UiState {
        object Loading : UiState
        object Default : UiState
        class Error(val error: String) : UiState
    }
}