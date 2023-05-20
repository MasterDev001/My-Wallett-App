package com.example.r_usecase.usecases.currencyUseCase

import com.example.z_entity.db.entity.MyCurrency
import com.example.z_entity.repository.CurrencyRepository
import javax.inject.Inject

class UpdateCurrencyUseCase @Inject constructor(private val currencyRepository: CurrencyRepository) {

    suspend operator fun invoke(myCurrency: MyCurrency) =
        currencyRepository.updateCurrency(myCurrency)
}