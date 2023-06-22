package com.example.r_usecase.usecases.personUseCase

import com.example.a_common.data.PersonData
import com.example.z_entity.db.entity.toMyPerson
import com.example.z_entity.repository.PersonsRepository
import javax.inject.Inject

class UpdatePersonUseC @Inject constructor(private val personsRepository: PersonsRepository) {

    suspend operator fun invoke(personData: PersonData) =
        personsRepository.updatePerson(personData.toMyPerson())
}