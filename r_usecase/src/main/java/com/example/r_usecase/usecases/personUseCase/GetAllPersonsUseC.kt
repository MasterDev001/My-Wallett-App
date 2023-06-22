package com.example.r_usecase.usecases.personUseCase

import com.example.z_entity.db.entity.toPersonData
import com.example.z_entity.repository.PersonsRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllPersonsUseC @Inject constructor(private val personsRepository: PersonsRepository) {

    suspend operator fun invoke() = personsRepository.getAllPersons().map { personsList ->
        personsList.map { it.toPersonData() }
    }
}