package com.example.r_usecase.usecases.personUseCase

data class PersonsUseCase(
    val addPersonUseC: AddPersonUseC,
    val updatePersonUseC: UpdatePersonUseC,
    val deletePersonUseC: DeletePersonUseC,
    val getPersonUseC: GetPersonUseC,
    val getAllPersonsUseC: GetAllPersonsUseC,
    val isPersonExistUseC: IsPersonExistUseC,
)