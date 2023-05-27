package com.example.r_usecase.usecases.walletsUseCase

import com.example.a_common.data.WalletData
import com.example.z_entity.db.entity.toWalletData
import com.example.z_entity.repository.WalletsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllWalletsUseC @Inject constructor(private val repository: WalletsRepository) {

    suspend operator fun invoke():Flow<List<WalletData>> = repository.getAllWallets().map { myWalletsList ->
        myWalletsList.map {
            it.toWalletData()
        }
    }
}