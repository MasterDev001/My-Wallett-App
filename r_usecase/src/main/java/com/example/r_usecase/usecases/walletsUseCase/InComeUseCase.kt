package com.example.r_usecase.usecases.walletsUseCase

import com.example.a_common.data.CurrencyData
import com.example.a_common.data.WalletData
import com.example.a_common.data.WalletOwnerData
import com.example.a_common.data.WalletOwnerDataList
import com.example.z_entity.db.entity.toMyWallet
import com.example.z_entity.repository.WalletsRepository
import javax.inject.Inject

class InComeUseCase @Inject constructor(private val repository: WalletsRepository) {

    suspend operator fun invoke(amount: Double, currencyData: CurrencyData, wallet: WalletData) {

        val c = wallet.walletOwnerDataList.walletOwnerData
        if (repository.isCurrencyIdExistsInWallet(
                wallet.id,
                currencyData.id
            )
        ) {
            c.forEach {
                if (it.id == currencyData.id) {
                    it.currencyBalance += amount
                }
            }
        } else {
            c.add(
                WalletOwnerData(
                    currencyData.id,
                    wallet.id,
                    currencyData.id,
                    amount,
                    currencyData.rate
                )
            )
        }
        repository.updateWallet(
            wallet.copy(walletOwnerDataList = WalletOwnerDataList(c)).toMyWallet()
        )
    }
}