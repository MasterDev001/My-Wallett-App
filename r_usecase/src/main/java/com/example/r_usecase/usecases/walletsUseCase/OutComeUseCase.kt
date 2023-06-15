package com.example.r_usecase.usecases.walletsUseCase

import com.example.a_common.data.WalletData
import com.example.a_common.data.WalletOwnerData
import com.example.a_common.data.WalletOwnerDataList
import com.example.z_entity.db.entity.toMyWallet
import com.example.z_entity.repository.WalletsRepository
import javax.inject.Inject

class OutComeUseCase @Inject constructor(private val walletRepository: WalletsRepository) {

    suspend operator fun invoke(
        amount: Double,
        wallet: WalletData,
        currentWalletOwner: WalletOwnerData,
        currentWalletOwnerIndex: Int
    ) {
        val updatedAmount = currentWalletOwner.currencyBalance - amount
        val updatedWalletOwnerList = wallet.walletOwnerDataList.walletOwnerData
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
    }
}