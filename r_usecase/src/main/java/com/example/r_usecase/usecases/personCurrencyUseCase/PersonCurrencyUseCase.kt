package com.example.r_usecase.usecases.personCurrencyUseCase

data class PersonCurrencyUseCase(
    val getPersonCurriesByPersonIdUseC: GetPersonCurriesByPersonIdUseC,
    val getAllDebtors: GetAllDebtors,
    val getAllLenders: GetAllLenders,
    val isPersonCurrencyExistUseC: IsPersonCurrencyExistUseC,
    val addPersonCurrencyUseC: AddPersonCurrencyUseC,
    val updatePersonCurrencyUseC: UpdatePersonCurrencyUseC,
    val deletePersonCurrencyUseC: DeletePersonCurrencyUseC,
    val isPersonCurrenciesCurrencyExistUseC: IsPersonCurrenciesCurrencyExistUseC,
    val getPersonCurrencyUseC: GetPersonCurrencyUseC
)