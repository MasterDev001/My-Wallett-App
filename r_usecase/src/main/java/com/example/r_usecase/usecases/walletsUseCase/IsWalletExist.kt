package com.example.r_usecase.usecases.walletsUseCase

import com.example.z_entity.repository.WalletsRepository
import javax.inject.Inject

class IsWalletExist @Inject constructor(private val repository: WalletsRepository) {

     operator fun invoke(name:String)=repository.isWalletNameExists(name)
}