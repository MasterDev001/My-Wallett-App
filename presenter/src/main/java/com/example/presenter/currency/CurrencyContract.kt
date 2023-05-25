package com.example.presenter.currency

import com.example.a_common.data.CurrencyData

class CurrencyContract {

    sealed interface Intent {
        object OpenHome : Intent
        class AddCurrency(val currency: CurrencyData) : Intent
        class UpdateCurrency(val currency: CurrencyData) : Intent
        class DeleteCurrency(val currency: CurrencyData) : Intent
    }

    interface UiState {
        object Default : UiState
        object Loading : UiState
        class Error(val error: String) : UiState
    }
}