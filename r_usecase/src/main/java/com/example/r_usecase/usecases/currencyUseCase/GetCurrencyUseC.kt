package com.example.r_usecase.usecases.currencyUseCase

import com.example.z_entity.db.entity.toCurrencyData
import com.example.z_entity.repository.CurrencyRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCurrencyUseC @Inject constructor(private val currencyRepository: CurrencyRepository) {

    suspend operator fun invoke(name: String) = currencyRepository.getCurrency(name).map {
        it.toCurrencyData()
    }
}