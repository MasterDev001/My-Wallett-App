package com.example.r_usecase.usecases.walletsUseCase

import com.example.a_common.data.WalletOwnerData
import com.example.z_entity.db.entity.toWalletOwnerData
import com.example.z_entity.repository.WalletsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetWalletOwnerListUseC @Inject constructor(private val walletsRepository: WalletsRepository) {

    suspend operator fun invoke(id: String): Flow<List<WalletOwnerData>> =
        walletsRepository.getWalletOwnerList(id).map { myWalletOwnerList ->
            myWalletOwnerList.myWalletOwners.map {
                it.toWalletOwnerData()
            }
        }
}