package com.example.presenter.lend

import com.example.a_common.data.CurrencyData
import com.example.a_common.data.PersonData
import com.example.a_common.data.WalletData
import com.example.a_common.data.WalletOwnerData

class LendContract {

    sealed interface Intent {
        object OpenHome : Intent
        class LendMoney(
            val personData: PersonData,
            val amount: String,
            val currencyData: CurrencyData,
            val wallet: WalletData,
            val selectedWalletOwner: WalletOwnerData,
            val comment: String
        ) : Intent
    }

    interface UiState {
        object Default : UiState
        object Loading : UiState
        class Error(val error: String) : UiState
    }
}