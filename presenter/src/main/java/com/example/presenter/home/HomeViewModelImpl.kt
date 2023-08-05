package com.example.presenter.home

import cafe.adriel.voyager.core.model.coroutineScope
import com.example.a_common.data.CurrencyData
import com.example.a_common.data.HistoryData
import com.example.a_common.data.PersonData
import com.example.a_common.data.WalletData
import com.example.r_usecase.usecases.currencyUseCase.CurrencyUseCase
import com.example.r_usecase.usecases.dataUseCase.DataUseCase
import com.example.r_usecase.usecases.historyUseCase.HistoryUseCase
import com.example.r_usecase.usecases.personUseCase.PersonsUseCase
import com.example.r_usecase.usecases.walletsUseCase.WalletsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class HomeViewModelImpl @Inject constructor(
    private val direction: HomeDirection,
    private val walletsUseCase: WalletsUseCase,
    private val currencyUseCase: CurrencyUseCase,
    private val personsUseCase: PersonsUseCase,
    private val historyUseCase: HistoryUseCase,
    private val dataUseCase: DataUseCase
) : HomeViewModel {

    init {
        coroutineScope.launch {
//            dataUseCase.downloadAllDataUseC.invoke(){
//
//            }
            if (dataUseCase.isNeedUpdateUseC.invoke()) {
                dataUseCase.loadedDataUseC.invoke()
            }
        }
    }

    override val uiState = MutableStateFlow(HomeContract.UiState.Default)

    override fun wallets(): List<WalletData> {
        val walletList = mutableListOf<WalletData>()
        coroutineScope.launch(Dispatchers.IO) {
            walletsUseCase.getAllWalletsUseC.invoke().collect {
                walletList.addAll(it)
            }
        }
        return walletList
    }

    override fun currencies(): List<CurrencyData> {
        val currencyList = mutableListOf<CurrencyData>()
        coroutineScope.launch(Dispatchers.IO) {
            currencyUseCase.getAllCurrencies.invoke().collect {
                currencyList.addAll(it)
            }
        }
        return currencyList
    }

    override fun getLimitedHistory(count: Int): Flow<List<HistoryData>> = flow {
        emitAll(historyUseCase.getLimitedHistoryUseC.invoke(count))
    }

    override fun persons(): List<PersonData> {
        val personList = mutableListOf<PersonData>()
        coroutineScope.launch(Dispatchers.IO) {
            personsUseCase.getAllPersonsUseC.invoke().collect {
                personList.addAll(it)
            }
        }
        return personList
    }

    override fun onEventDispatcher(intent: HomeContract.Intent) {
        when (intent) {
            is HomeContract.Intent.OpenOutCome -> {
                coroutineScope.launch { direction.navigateToOutCome() }
            }

            is HomeContract.Intent.OpenCurrency -> {
                coroutineScope.launch { direction.navigateToCurrencies() }
            }

            is HomeContract.Intent.OpenInCome -> {
                coroutineScope.launch { direction.navigateToInCome() }
            }

            is HomeContract.Intent.OpenLend -> {
                coroutineScope.launch {
                    direction.navigateToLend(
                        intent.persons,
                        intent.currencies,
                        intent.wallets
                    )
                }
            }

            is HomeContract.Intent.OpenBorrow -> {
                coroutineScope.launch {
                    direction.navigateToBorrow(intent.persons, intent.currencies, intent.wallets)
                }
            }

            is HomeContract.Intent.OpenPersons -> {
                coroutineScope.launch {
                    direction.navigateToPersons()
                }
            }

            is HomeContract.Intent.OpenSettings -> {
                coroutineScope.launch { direction.navigateToSettings() }
            }

            is HomeContract.Intent.OpenHistory -> {
                coroutineScope.launch { direction.navigateToHistory() }
            }

            is HomeContract.Intent.OpenWallets -> {
                coroutineScope.launch { direction.navigateToWallets() }
            }

            is HomeContract.Intent.OpenConvert -> {
                coroutineScope.launch {
                    direction.navigateToConvert(
                        intent.currencies,
                        intent.wallets
                    )
                }
            }
        }
    }
}