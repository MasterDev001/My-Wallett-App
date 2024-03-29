package com.example.r_usecase.usecases.currencyUseCase

import com.example.z_entity.repository.CurrencyRepository
import javax.inject.Inject

class GetTotalBalanceUseC @Inject constructor(private val currencyRepository: CurrencyRepository) {

    suspend operator fun invoke() = currencyRepository.getTotalBalance()
}