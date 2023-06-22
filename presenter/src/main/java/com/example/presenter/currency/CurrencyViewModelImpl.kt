package com.example.presenter.currency

import cafe.adriel.voyager.core.model.coroutineScope
import com.example.a_common.data.CurrencyData
import com.example.r_usecase.usecases.currencyUseCase.CurrencyUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class CurrencyViewModelImpl @Inject constructor(
    private val currencyUseCase: CurrencyUseCase,
    private val direction: CurrencyDirection
) : CurrencyViewModel {

    override val currencies: Flow<List<CurrencyData>> = flow {
        emitAll(currencyUseCase.getAllCurrencies.invoke())
    }
    override val uiState =
        MutableStateFlow<CurrencyContract.UiState>(CurrencyContract.UiState.Default)

    override fun isCurrencyExist(name: String): Boolean {
        return currencyUseCase.isCurrencyExistUseC.invoke(name)
    }

    override fun onEventDispatcher(intent: CurrencyContract.Intent) {
        when (intent) {
            is CurrencyContract.Intent.AddCurrency -> {
                uiState.value = CurrencyContract.UiState.Loading
                coroutineScope.launch(Dispatchers.IO) {
                    currencyUseCase.addCurrency.invoke(intent.currency)
                }
            }

            is CurrencyContract.Intent.UpdateCurrency -> {
                uiState.value = CurrencyContract.UiState.Loading
                coroutineScope.launch(Dispatchers.IO) {
                    currencyUseCase.updateCurrency.invoke(intent.currency)
                }
            }

            is CurrencyContract.Intent.DeleteCurrency -> {
                uiState.value = CurrencyContract.UiState.Loading
                coroutineScope.launch(Dispatchers.IO) {
                    currencyUseCase.deleteCurrency.invoke(intent.currency)
                }
            }

            is
            CurrencyContract.Intent.OpenHome -> coroutineScope.launch { direction.back() }
        }
    }
}