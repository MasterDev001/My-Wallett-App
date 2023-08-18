package com.example.r_usecase.usecases.walletsUseCase

import com.example.a_common.data.CurrencyData
import com.example.a_common.data.WalletData
import com.example.a_common.data.WalletOwnerData
import com.example.a_common.data.WalletOwnerDataList
import com.example.r_usecase.usecases.currencyUseCase.CurrencyUseCase
import com.example.z_entity.db.entity.toMyWallet
import com.example.z_entity.repository.WalletsRepository
import javax.inject.Inject

class OutComeUseCase @Inject constructor(
    private val walletRepository: WalletsRepository,
    private val currencyUseCase: CurrencyUseCase
) {

    suspend operator fun invoke(
        amount: Double,
        wallet: WalletData,
        currentWalletOwner: WalletOwnerData,
        currencyData: CurrencyData,
    ) {

        var currentWalletOwnerIndex = 0
        val updatedAmount = currentWalletOwner.currencyBalance - amount
        val updatedWalletOwnerList = wallet.walletOwnerDataList.walletOwnerData
        for (i in 0 until updatedWalletOwnerList.size) {
            if (updatedWalletOwnerList[i].id == currentWalletOwner.id) {
                currentWalletOwnerIndex = i
            }
        }
        if (updatedAmount != 0.0) {
            updatedWalletOwnerList[currentWalletOwnerIndex] =
                currentWalletOwner.copy(currencyBalance = updatedAmount)
        } else {
            updatedWalletOwnerList.removeAt(currentWalletOwnerIndex)
        }
        walletRepository.updateWallet(
            wallet.copy(
                walletOwnerDataList = WalletOwnerDataList(
                    updatedWalletOwnerList
                )
            ).toMyWallet()
        )
        currencyUseCase.updateCurrency.invoke(currencyData.copy(balance = currencyData.balance - amount))

    }
}