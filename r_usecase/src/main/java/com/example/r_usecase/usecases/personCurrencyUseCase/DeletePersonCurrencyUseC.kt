package com.example.r_usecase.usecases.personCurrencyUseCase

import com.example.z_entity.repository.PersonCurrencyRepository
import javax.inject.Inject

class DeletePersonCurrencyUseC @Inject constructor(private val personCurrencyRepository: PersonCurrencyRepository) {

    suspend operator fun invoke(id:String) =
        personCurrencyRepository.deletePersonCurrency(id)
}