package com.example.r_usecase.usecases.personCurrencyUseCase

import com.example.a_common.data.PersonCurrencyData
import com.example.z_entity.db.models.toMyPersonCurrency
import com.example.z_entity.repository.PersonCurrencyRepository
import javax.inject.Inject

class UpdatePersonCurrencyUseC @Inject constructor(private val personCurrencyRepository: PersonCurrencyRepository) {

    suspend operator fun invoke(personCurrencyData: PersonCurrencyData) =
        personCurrencyRepository.updatePersonCurrency(personCurrencyData.toMyPersonCurrency())
}