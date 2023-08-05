package com.example.r_usecase.usecases.dataUseCase

import com.example.z_entity.repository.DataRepository
import javax.inject.Inject

class IsNeedUpdateUseC @Inject constructor(private val dataRepository: DataRepository) {

    suspend operator fun invoke(): Boolean = dataRepository.isNeedUpdate()
}