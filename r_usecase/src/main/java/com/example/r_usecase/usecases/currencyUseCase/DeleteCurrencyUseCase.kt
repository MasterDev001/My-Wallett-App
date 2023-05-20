package com.example.r_usecase.usecases.currencyUseCase

import MyCurrency
import com.example.repository.CurrencyRepository
import javax.inject.Inject

class DeleteCurrencyUseCase @Inject constructor(private val currencyRepository: CurrencyRepository) {

    suspend operator fun invoke(currency: MyCurrency) = currencyRepository.deleteCurrency(currency)
}