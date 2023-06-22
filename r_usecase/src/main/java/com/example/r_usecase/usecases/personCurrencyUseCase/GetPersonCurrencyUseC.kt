package com.example.r_usecase.usecases.personCurrencyUseCase

import com.example.z_entity.db.models.toPersonCurrencyData
import com.example.z_entity.repository.PersonCurrencyRepository
import javax.inject.Inject

class GetPersonCurrencyUseC @Inject constructor(private val personCurrencyRepository: PersonCurrencyRepository) {

    operator fun invoke(personId: String, currencyId: String) =
        personCurrencyRepository.getPersonCurrency(personId, currencyId).toPersonCurrencyData()
}