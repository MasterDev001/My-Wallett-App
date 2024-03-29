package com.example.r_usecase.usecases.currencyUseCase

data class CurrencyUseCase(
    val addCurrency: AddCurrencyUseCase,
    val deleteCurrency: DeleteCurrencyUseCase,
    val updateCurrency: UpdateCurrencyUseCase,
    val getCurrency: GetCurrencyUseC,
    val getAllCurrencies: GetAllCurrenciesUseC,
    val isCurrencyExistUseC: IsCurrencyExistUseC,
    val getTotalBalanceUseC: GetTotalBalanceUseC
)
