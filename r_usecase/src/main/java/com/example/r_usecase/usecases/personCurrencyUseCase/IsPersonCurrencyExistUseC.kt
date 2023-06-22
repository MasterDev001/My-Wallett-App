package com.example.r_usecase.usecases.personCurrencyUseCase

import com.example.z_entity.repository.PersonCurrencyRepository
import javax.inject.Inject

class IsPersonCurrencyExistUseC @Inject constructor(private val personCurrencyRepository: PersonCurrencyRepository) {

    operator fun invoke(personId: String) =
        personCurrencyRepository.isPersonCurrencyExists(personId)
}