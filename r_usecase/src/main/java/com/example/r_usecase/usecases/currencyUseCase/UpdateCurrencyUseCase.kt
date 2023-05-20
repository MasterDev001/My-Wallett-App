package com.example.r_usecase.usecases.currencyUseCase

import MyCurrency
import com.example.repository.CurrencyRepository
import javax.inject.Inject

class UpdateCurrencyUseCase @Inject constructor(private val currencyRepository: CurrencyRepository) {

    suspend operator fun invoke(myCurrency: MyCurrency) =
        currencyRepository.updateCurrency(myCurrency)
}