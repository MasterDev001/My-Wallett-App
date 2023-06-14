package com.example.r_usecase.usecases.currencyUseCase

import com.example.z_entity.db.entity.toCurrencyData
import com.example.z_entity.repository.CurrencyRepository
import javax.inject.Inject

class GetCurrencyUseC @Inject constructor(private val currencyRepository: CurrencyRepository) {

     operator fun invoke(id: String) = currencyRepository.getCurrency(id).toCurrencyData()

}