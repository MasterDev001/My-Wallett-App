package com.example.r_usecase.usecases.walletsUseCase

import com.example.a_common.data.WalletData
import com.example.z_entity.db.entity.toMyWallet
import com.example.z_entity.repository.WalletsRepository
import javax.inject.Inject

class UpdateWalletUseC @Inject constructor(private val repository: WalletsRepository) {

    suspend operator fun invoke(walletData: WalletData) =
        repository.updateWallet(walletData.toMyWallet())
}