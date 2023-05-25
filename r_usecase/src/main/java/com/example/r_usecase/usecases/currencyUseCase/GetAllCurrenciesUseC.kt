package com.example.r_usecase.usecases.currencyUseCase

import com.example.a_common.data.CurrencyData
import com.example.z_entity.db.entity.toCurrencyData
import com.example.z_entity.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllCurrenciesUseC @Inject constructor(private val currencyRepository: CurrencyRepository) {

    suspend operator fun invoke(): Flow<List<CurrencyData>> {
        return currencyRepository.getAllCurrencies().map { currencyDataList ->
            currencyDataList.map { it.toCurrencyData() }
        }
    }
}