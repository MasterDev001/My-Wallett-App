package com.example.r_usecase.usecases.walletsUseCase

import com.example.a_common.data.WalletData
import com.example.z_entity.db.entity.toMyWallet
import com.example.z_entity.repository.WalletsRepository
import javax.inject.Inject

class AddWalletUseC @Inject constructor(private val walletsRepository: WalletsRepository) {

    suspend operator fun invoke(walletData: WalletData) =
        walletsRepository.addWallet(walletData.toMyWallet())
}