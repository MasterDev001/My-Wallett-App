package com.example.r_usecase.usecases.personCurrencyUseCase

import com.example.z_entity.db.models.toPersonCurrencyData
import com.example.z_entity.repository.PersonCurrencyRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllDebtors @Inject constructor(private val personCurrencyRepository: PersonCurrencyRepository) {

    suspend operator fun invoke() =
        personCurrencyRepository.getAllDebtors().map { debtorList ->
            debtorList.map { it.toPersonCurrencyData() }
        }
}