package com.example.mywallett.app.screens.outcome_currencies

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import cafe.adriel.voyager.androidx.AndroidScreen
import com.example.a_common.data.CurrencyData
import com.example.a_common.data.WalletData
import com.example.a_common.data.WalletOwnerData
import com.example.mywallett.R
import com.example.mywallett.app.utils.OutComeCurrencyItem
import com.example.mywallett.app.utils.OutComeDialog
import com.example.mywallett.app.utils.horizontalPadding_16
import com.example.presenter.outCome_currencies.OutComeCurrenciesContract
import com.example.presenter.outCome_currencies.OutComeCurrenciesViewM
import uz.gita.vogayerlib.hiltScreenModel

class OutComeCurrenciesScreen(private val wallet: WalletData) : AndroidScreen() {

    @Composable
    override fun Content() {
        val viewModel: OutComeCurrenciesViewM = hiltScreenModel()
        val uiState = viewModel.uiState.collectAsState()
        OutComeCurrenciesContent(uiState, viewModel, viewModel::onEventDispatcher)
    }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    private fun OutComeCurrenciesContent(
        uiState: State<OutComeCurrenciesContract.UiState>,
        viewModel: OutComeCurrenciesViewM,
        onEvent: (OutComeCurrenciesContract.Intent) -> Unit
    ) {

        var currentCurrency by remember { mutableStateOf(CurrencyData("")) }
        var currentWalletOwnerIndex by remember { mutableStateOf(-1) }
        var currentWalletOwner by remember { mutableStateOf(WalletOwnerData("")) }
        var outComeDialog by remember { mutableStateOf(false) }

        if (outComeDialog) {
            OutComeDialog(
                currentCurrency.name,
                currentWalletOwner.currencyBalance,
                wallet.name,
                onDismiss = {
                    outComeDialog = false
                }, onConfirmClick = { amount, comment ->
                    onEvent.invoke(
                        OutComeCurrenciesContract.Intent.OutComeMoney(
                            amount,
                            comment,
                            currentCurrency,
                            wallet,
                            currentWalletOwner,
                            currentWalletOwnerIndex
                        )
                    )
                })
        }

        Scaffold(topBar = {
            TopAppBar(title = { Text(text = stringResource(id = R.string.kirim)) },
                navigationIcon = {
                    IconButton(onClick = {
                        onEvent.invoke(OutComeCurrenciesContract.Intent.OpenOutCome)
                    }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back")
                    }
                })
        }) {
            Column(
                Modifier
                    .background(Color.Gray)
                    .padding(horizontal = horizontalPadding_16)
                    .fillMaxSize()
            ) {
                LazyColumn {
                    items(count = wallet.walletOwnerDataList.walletOwnerData.count()) {
                        val item = wallet.walletOwnerDataList.walletOwnerData[it]
                        val currency = viewModel.getCurrency(item.currencyId)

                        OutComeCurrencyItem(item, currency.name, onItemClick = {
                            currentWalletOwnerIndex = it
                            currentWalletOwner = item
                            currentCurrency = currency
                            outComeDialog = true
                        })
                    }
                }
            }
        }
    }
}