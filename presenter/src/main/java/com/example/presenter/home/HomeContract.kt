package com.example.presenter.home

class HomeContract {

    sealed interface Intent{
        object OpenSettings:Intent
        object OpenKirim:Intent
        object OpenChiqim:Intent
        object OpenQarzOlish:Intent
        object OpenQarzBerish:Intent
        object OpenQarzdorlar:Intent
        object OpenHaqdorlar:Intent
        object OpenTarix:Intent
        object OpenCurrency:Intent
        object OpenWallets:Intent
    }

    interface UiState {
        object Default : UiState
        object Loading : UiState
        class Error(val message: String = "") : UiState
    }
}