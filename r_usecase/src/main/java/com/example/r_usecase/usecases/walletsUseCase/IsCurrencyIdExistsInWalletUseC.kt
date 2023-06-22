package com.example.r_usecase.usecases.walletsUseCase

import com.example.z_entity.repository.WalletsRepository
import javax.inject.Inject

class IsCurrencyIdExistsInWalletUseC @Inject constructor(private val walletsRepository: WalletsRepository) {

     operator fun invoke(walletId: String, currencyId: String) =
        walletsRepository.isCurrencyIdExistsInWallet(walletId, currencyId)
}