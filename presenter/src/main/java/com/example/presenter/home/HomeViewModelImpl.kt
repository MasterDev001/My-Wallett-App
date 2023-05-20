package com.example.presenter.home

import cafe.adriel.voyager.core.model.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class HomeViewModelImpl @Inject constructor(
    private val direction: HomeDirection
) : HomeViewModel {

    override val uiState = MutableStateFlow(HomeContract.UiState.Default)

    override fun onEventDispatcher(intent: HomeContract.Intent) {
        when (intent) {
            is HomeContract.Intent.OpenChiqim -> {

            }

            is HomeContract.Intent.OpenCurrency -> {
                coroutineScope.launch { direction.navigateToCurrencies() }
            }

            is HomeContract.Intent.OpenHaqdorlar -> {

            }

            is HomeContract.Intent.OpenKirim -> {

            }

            is HomeContract.Intent.OpenQarzBerish -> {

            }

            is HomeContract.Intent.OpenQarzOlish -> {

            }

            is HomeContract.Intent.OpenQarzdorlar -> {

            }

            is HomeContract.Intent.OpenSettings -> {

            }

            is HomeContract.Intent.OpenTarix -> {

            }
        }
    }
}