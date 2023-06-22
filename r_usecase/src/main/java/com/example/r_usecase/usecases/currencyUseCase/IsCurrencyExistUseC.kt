package com.example.r_usecase.usecases.currencyUseCase

import com.example.z_entity.repository.CurrencyRepository
import javax.inject.Inject

class IsCurrencyExistUseC @Inject constructor(private val currencyRepository: CurrencyRepository) {

    operator fun invoke(currencyName: String) = currencyRepository.isCurrencyExist(currencyName)
}