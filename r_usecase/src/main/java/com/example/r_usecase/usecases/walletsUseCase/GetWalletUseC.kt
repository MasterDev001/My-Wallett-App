package com.example.r_usecase.usecases.walletsUseCase

import com.example.z_entity.db.entity.toWalletData
import com.example.z_entity.repository.WalletsRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetWalletUseC @Inject constructor(private val repository: WalletsRepository) {

    suspend operator fun invoke(name:String)=repository.getWallet(name).map {
        it.toWalletData()
    }
}