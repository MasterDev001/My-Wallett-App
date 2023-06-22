package com.example.r_usecase.usecases.personUseCase

import com.example.z_entity.db.entity.toPersonData
import com.example.z_entity.repository.PersonsRepository
import javax.inject.Inject

class GetPersonUseC @Inject constructor(private val personsRepository: PersonsRepository) {

     operator fun invoke(personId: String) =
        personsRepository.getPerson(personId).toPersonData()
}