package com.example.presenter.history

class HistoryContract {

    sealed interface Intent {
        object OpenHome : Intent
    }

    interface UiState {
        object Default : UiState
        object Loading : UiState
        class Error(val error: String) : UiState
    }
}