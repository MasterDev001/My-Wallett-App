package com.example.presenter.wallets

import com.example.a_common.data.WalletData

class WalletsContract {

    sealed interface Intent{
        object OpenHome:Intent
        class AddWallet(val walletData:WalletData):Intent
        class UpdateWallet(val walletData: WalletData):Intent
        class DeleteWallet(val walletData: WalletData):Intent
    }

    interface UiState{
        object Default:UiState
        object Loading:UiState
        class Error(val error:String=""):UiState
    }
}