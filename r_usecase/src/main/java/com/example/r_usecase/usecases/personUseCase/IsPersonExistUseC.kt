package com.example.r_usecase.usecases.personUseCase

import com.example.z_entity.repository.PersonsRepository
import javax.inject.Inject

class IsPersonExistUseC @Inject constructor(private val personsRepository: PersonsRepository){

    operator fun invoke(personName:String)=personsRepository.isPersonExist(personName)
}