package com.example.mywallett.app.screens.currencies

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.FloatingActionButton
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import com.example.a_common.data.CurrencyData
import com.example.mywallett.R
import com.example.mywallett.app.utils.CurrencyItem
import com.example.mywallett.app.utils.DialogAddCurrency
import com.example.mywallett.app.utils.DialogConfirm
import com.example.mywallett.app.utils.DialogEditCurrency
import com.example.mywallett.app.utils.PopUpToDialog
import com.example.mywallett.app.utils.horizontalPadding_16
import com.example.mywallett.ui.theme.ColorGreenButton
import com.example.presenter.currency.CurrencyContract
import com.example.presenter.currency.CurrencyViewModel
import uz.gita.vogayerlib.hiltScreenModel
import java.util.UUID

class CurrencyScreen : AndroidScreen() {

    @Composable
    override fun Content() {
        val viewModel: CurrencyViewModel = hiltScreenModel()
        val uiState = viewModel.uiState.collectAsState()
        CurrencyContent(uiState, viewModel, viewModel::onEventDispatcher)
    }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    private fun CurrencyContent(
        uiState: State<CurrencyContract.UiState>,
        viewModel: CurrencyViewModel,
        onEvent: (CurrencyContract.Intent) -> Unit
    ) {

        val currencyList by viewModel.currencies.collectAsState(emptyList())
        val popUpState = remember { mutableStateOf(false) }
        val editDialogState = remember { mutableStateOf(false) }
        val deleteDialogState = remember { mutableStateOf(false) }
        var offsetPopUp by remember { mutableStateOf(Offset(0f, 0f)) }
        val itemHeight by remember { mutableStateOf(0.dp) }
        val density = LocalDensity.current
        var addDialogState by remember { mutableStateOf(false) }
        var currencyName by remember { mutableStateOf("") }
        var currencyRate by remember { mutableStateOf("") }
        var currentCurrency by remember { mutableStateOf(CurrencyData("")) }

        if (popUpState.value) {
            PopUpToDialog(offset = offsetPopUp,
                itemHeight = itemHeight,
                text = currentCurrency.name,
                onDeleteClick = {
                    deleteDialogState.value = true
                },
                onEditClick = {
                    editDialogState.value = true
                },
                onDismissRequest = { popUpState.value = false })
        }

        if (editDialogState.value) {
            popUpState.value = false
            DialogEditCurrency(currentCurrency,
                onDismissRequest = { editDialogState.value = false },
                onAddClick = { newCurrency ->
                    onEvent.invoke(CurrencyContract.Intent.UpdateCurrency(newCurrency))
                })
        }

        if (deleteDialogState.value) {
            popUpState.value = false
            DialogConfirm(
                text = currentCurrency.name,
                message = currentCurrency.name + stringResource(R.string.ni_o_chirishga_ishonchingiz_komilmi),
                onDismiss = {
                    deleteDialogState.value = false
                },
                onConfirm = {
                    onEvent.invoke(CurrencyContract.Intent.DeleteCurrency(currentCurrency))
                    deleteDialogState.value = false
                })
        }
        if (addDialogState) {
            DialogAddCurrency(
                currencyName,
                currencyRate,
                currencyNameOnValueChange = { currencyName = it },
                currencyRateOnValueChange = { currencyRate = it },
                onDismissRequest = { addDialogState = false },
                onAddClick = {
                    onEvent.invoke(
                        CurrencyContract.Intent.AddCurrency(
                            CurrencyData(
                                UUID.randomUUID().toString(),
                                currencyName,
                                currencyRate.toDouble(),
                                System.currentTimeMillis()
                            )
                        )
                    )
                    addDialogState = false
                })
        }

        Scaffold(topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.valyutalar)) },
                navigationIcon = {
                    IconButton(onClick = {
                        onEvent.invoke(CurrencyContract.Intent.OpenHome)
                    }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                backgroundColor = Color.Red
            )
        }, floatingActionButton = {
            FloatingActionButton(onClick = {
                addDialogState = true
            }, backgroundColor = ColorGreenButton) {
                Image(
                    painter = painterResource(id = R.drawable.add_white),
                    contentDescription = "add",
                )
            }
        }) {
            Column(
                Modifier
                    .background(Color.Gray)
                    .padding(horizontal = horizontalPadding_16)
                    .fillMaxSize(),
            ) {
                LazyColumn {
                    items(count = currencyList.count(), itemContent = {
                        val item = currencyList[it]
                        CurrencyItem(itemHeight, density, item, offsetPopUp, popUpState) { offset ->
                            offsetPopUp = offset
                            currentCurrency = currencyList[it]
                        }
                    })
                }
            }
        }
    }
}
