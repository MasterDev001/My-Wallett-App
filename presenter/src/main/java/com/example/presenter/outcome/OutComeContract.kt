package com.example.presenter.outcome

import com.example.a_common.data.WalletData

class OutComeContract {

    sealed interface Intent {
        object OpenHome : Intent
        class OpenOutComeCurrencies(val wallet: WalletData) : Intent
    }

    interface UiState {
        object Loading : UiState
        object Default : UiState
        class Error(val error: String) : UiState
    }
}