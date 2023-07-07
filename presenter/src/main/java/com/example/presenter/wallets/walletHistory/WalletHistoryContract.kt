package com.example.presenter.wallets.walletHistory

class WalletHistoryContract {

    sealed interface Intent {
        object OpenHome : Intent
    }

    data class UiState(
        val isLoading: Boolean? = null,
        val message: String? = null,
        val error: String? = null,
    )
}