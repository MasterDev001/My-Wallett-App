package com.example.r_usecase.usecases.currencyUseCase

import com.example.repository.CurrencyRepository
import javax.inject.Inject

class GetAllCurrenciesUseC @Inject constructor(private val currencyRepository: CurrencyRepository) {

suspend operator fun invoke()=currencyRepository.getAllCurrencies()
}