package com.example.r_usecase.usecases.personCurrencyUseCase

import com.example.z_entity.repository.PersonCurrencyRepository
import javax.inject.Inject

class IsPersonCurrenciesCurrencyExistUseC @Inject constructor(private val personCurrencyRepository: PersonCurrencyRepository) {

    operator fun invoke(personId: String,currencyId:String) =
        personCurrencyRepository.isPersonCurrenciesCurrencyExists(personId,currencyId)
}