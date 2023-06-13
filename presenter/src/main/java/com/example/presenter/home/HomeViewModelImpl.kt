package com.example.presenter.home

import cafe.adriel.voyager.core.model.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class HomeViewModelImpl @Inject constructor(
    private val direction: HomeDirection
) : HomeViewModel {

    override val uiState = MutableStateFlow(HomeContract.UiState.Default)

    override fun onEventDispatcher(intent: HomeContract.Intent) {
        when (intent) {
            is HomeContract.Intent.OpenOutCome -> {
                coroutineScope.launch { direction.navigateToOutCome() }
            }

            is HomeContract.Intent.OpenCurrency -> {
                coroutineScope.launch { direction.navigateToCurrencies() }
            }

            is HomeContract.Intent.OpenHaqdorlar -> {
                coroutineScope.launch { direction.navigateToCurrencies() }

            }

            is HomeContract.Intent.OpenInCome -> {
                coroutineScope.launch { direction.navigateToInCome() }
            }

            is HomeContract.Intent.OpenQarzBerish -> {

            }

            is HomeContract.Intent.OpenQarzOlish -> {

            }

            is HomeContract.Intent.OpenQarzdorlar -> {

            }

            is HomeContract.Intent.OpenSettings -> {

            }

            is HomeContract.Intent.OpenHistory -> {
                coroutineScope.launch { direction.navigateToHistory() }
            }

            is HomeContract.Intent.OpenWallets -> {
                coroutineScope.launch { direction.navigateToWallets() }
            }
        }
    }
}