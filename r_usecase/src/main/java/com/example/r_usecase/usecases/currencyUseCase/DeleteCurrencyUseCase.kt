package com.example.r_usecase.usecases.currencyUseCase

import com.example.a_common.data.CurrencyData
import com.example.z_entity.db.entity.toMyCurrency
import com.example.z_entity.repository.CurrencyRepository
import javax.inject.Inject

class DeleteCurrencyUseCase @Inject constructor(private val currencyRepository: CurrencyRepository) {

    suspend operator fun invoke(currency: CurrencyData) =
        currencyRepository.deleteCurrency(currency.toMyCurrency())
}