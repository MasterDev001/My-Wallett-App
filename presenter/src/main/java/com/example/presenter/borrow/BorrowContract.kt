package com.example.presenter.borrow

import com.example.a_common.data.CurrencyData
import com.example.a_common.data.PersonData
import com.example.a_common.data.WalletData

class BorrowContract {

    sealed interface Intent {
        object OpenHome : Intent
        class BorrowMoney(
            val personData: PersonData,
            val amount: String,
            val currencyData: CurrencyData,
            val wallet: WalletData,
            val comment: String
        ) : Intent
    }

    interface UiState {
        object Default : UiState
        object Loading : UiState
        class Error(val error: String) : UiState
    }
}