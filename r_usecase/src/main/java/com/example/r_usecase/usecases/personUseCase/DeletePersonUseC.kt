package com.example.r_usecase.usecases.personUseCase

import com.example.z_entity.repository.PersonsRepository
import javax.inject.Inject

class DeletePersonUseC @Inject constructor(private val personsRepository: PersonsRepository) {

    suspend operator fun invoke(personId: String) = personsRepository.deletePerson(personId)

}