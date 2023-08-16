package com.example.presenter.share


class ShareContract {

    sealed interface Intent {
        object OpenHome : Intent
    }

    interface UiState {
        object Default : UiState
        object Loading : UiState
        class Error(val message: String = "") : UiState
    }
}
