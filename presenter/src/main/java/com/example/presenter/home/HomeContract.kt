package com.example.presenter.home

import com.example.a_common.data.CurrencyData
import com.example.a_common.data.PersonData
import com.example.a_common.data.WalletData

class HomeContract {

    sealed interface Intent {
        object OpenSettings : Intent
        object OpenInCome : Intent
        object OpenOutCome : Intent
        class OpenBorrow(
            val persons: List<PersonData>,
            val currencies: List<CurrencyData>,
            val wallets: List<WalletData>
        ) : Intent
        object OpenLend : Intent
        object OpenPersons : Intent
        object OpenHistory : Intent
        object OpenCurrency : Intent
        object OpenWallets : Intent
    }

    interface UiState {
        object Default : UiState
        object Loading : UiState
        class Error(val message: String = "") : UiState
    }
}