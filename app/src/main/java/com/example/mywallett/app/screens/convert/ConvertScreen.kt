package com.example.mywallett.app.screens.convert

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import com.example.a_common.data.CurrencyData
import com.example.a_common.data.WalletData
import com.example.a_common.data.WalletOwnerData
import com.example.mywallett.R
import com.example.mywallett.app.utils.ConvertWalletDropDown
import com.example.mywallett.app.utils.CurrencyDropDown
import com.example.mywallett.app.utils.DialogAlert
import com.example.mywallett.app.utils.DialogButton
import com.example.mywallett.app.utils.DialogConfirm
import com.example.mywallett.app.utils.cornerRadius_8
import com.example.mywallett.app.utils.horizontalPadding_16
import com.example.mywallett.app.utils.textSize_26sp
import com.example.mywallett.ui.theme.ColorBorderGray
import com.example.presenter.convert.ConvertContract
import com.example.presenter.convert.ConvertViewModel
import uz.gita.vogayerlib.hiltScreenModel

class ConvertScreen(
    private val currenciesList: List<CurrencyData>,
    private val walletsList: List<WalletData>
) :
    AndroidScreen() {

    @Composable
    override fun Content() {
        val viewModel: ConvertViewModel = hiltScreenModel()
        val uiState = viewModel.uiState.collectAsState()
        ConvertScreenContent(uiState, viewModel, viewModel::onEventDispatcher)
    }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    private fun ConvertScreenContent(
        uiState: State<ConvertContract.UiState>,
        viewModel: ConvertViewModel,
        onEvent: (ConvertContract.Intent) -> Unit
    ) {
        var confirmDialogState by remember { mutableStateOf(false) }
        var amountTransaction by rememberSaveable { mutableStateOf("") }
        var isErrorAmount by remember { mutableStateOf(false) }
        var isErrorRate by remember { mutableStateOf(false) }
        var dialogAlert by remember { mutableStateOf(false) }
        var currencyRate by remember { mutableStateOf("1.0") }
        var fromCurrency by remember { mutableStateOf(CurrencyData("")) }
        var toCurrency by remember { mutableStateOf(CurrencyData("")) }
        var fromWallet by remember { mutableStateOf(WalletData("")) }
        var toWallet by remember { mutableStateOf(WalletData("")) }
        var fromWalletOwner by remember { mutableStateOf<WalletOwnerData?>(null) }
        var toWalletOwner by remember { mutableStateOf<WalletOwnerData?>(null) }

        if (confirmDialogState) {
            DialogConfirm(
                text = fromCurrency.name,
                message = "${fromWallet.name} dan $amountTransaction ${fromCurrency.name} miqdorida ${toWallet.name} ga $amountTransaction ${toCurrency.name} ayriboshlashga ishonchigiz komilmi?",
                onDismiss = { confirmDialogState = false },
                onConfirm = {
                    onEvent.invoke(
                        ConvertContract.Intent.ConvertMoney(
                            amountTransaction,
                            fromWalletOwner!!,
                            fromWallet,
                            toWallet,
                            toCurrency,
                            currencyRate.trim()
                        )
                    )
                })
        }

        if (dialogAlert) {
            DialogAlert(text = stringResource(R.string.bir_hamyonda_bir_xil_valyutani_ayriboshlash_mumkin_emas)) {
                dialogAlert = false
            }
        }

        Scaffold(topBar =
        {
            TopAppBar(title = { Text(text = stringResource(R.string.ayriboshlash)) },
                navigationIcon = {
                    IconButton(onClick = {
                        onEvent.invoke(ConvertContract.Intent.OpenHome)
                    }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back")
                    }
                })
        })
        {
            if (currenciesList.isEmpty()) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(R.string.valyuta_mavjud_emas),
                        fontSize = textSize_26sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            } else {
                Column(
                    Modifier.padding(horizontalPadding_16),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(
                        text = stringResource(R.string.qaysi_hamyondan),
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                            .fillMaxWidth(),
                    )

                    ConvertWalletDropDown(
                        Modifier
                            .padding(5.dp)
                            .fillMaxWidth(),
                        viewModel,
                        fromCurrency,
                        list = walletsList,
                        selectedWallet = { fromWallet = it }, selectedWalletOwner = {
                            fromWalletOwner = it
                        })
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedTextField(value = amountTransaction,
                            onValueChange = {
                                if (amountTransaction != "") isErrorAmount = false
                                amountTransaction = it
                            },
                            modifier = Modifier
                                .padding(5.dp)
                                .weight(2f),
                            shape = RoundedCornerShape(cornerRadius_8),
                            isError = isErrorAmount,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Decimal, imeAction = ImeAction.Done
                            ),
                            label = { Text(text = stringResource(R.string.summani_kiriting)) }
                        )
                        CurrencyDropDown(
                            Modifier.weight(1f),
                            list = currenciesList,
                            selectedCurrency = { fromCurrency = it })
                    }
                    Image(
                        painter = painterResource(id = R.drawable.convertation),
                        contentDescription = ""
                    )
                    Text(
                        text = stringResource(R.string.qaysi_hamyonga),
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                            .fillMaxWidth(),
                    )

                    ConvertWalletDropDown(
                        Modifier
                            .padding(5.dp)
                            .fillMaxWidth(),
                        viewModel,
                        toCurrency,
                        list = walletsList,
                        selectedWallet = { toWallet = it }, selectedWalletOwner = {
                            toWalletOwner = it
                        })
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(R.string.qaysi_valyutadan),
                            modifier = Modifier
                                .padding(horizontal = 5.dp)
                                .weight(1f),
                        )
                        CurrencyDropDown(
                            Modifier.weight(1f),
                            list = currenciesList,
                            selectedCurrency = { toCurrency = it })
                    }

                    if (fromCurrency.id != toCurrency.id) {
                        Row(
                            Modifier
                                .padding(cornerRadius_8)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Text(text = "Kurs 1 ${fromCurrency.name} = ")

                            OutlinedTextField(
                                modifier = Modifier.padding(5.dp),
                                value = currencyRate,
                                maxLines = 1,
                                onValueChange = {
                                    if (currencyRate.toFloatOrNull() != null) isErrorRate = false
                                    currencyRate = it
                                },
                                label = {
                                    Text(text = stringResource(R.string.qiymati))
                                },
                                shape = RoundedCornerShape(cornerRadius_8),
                                isError = isErrorRate,
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Decimal, imeAction = ImeAction.Done
                                )
                            )
                        }
                    }
                    Row(
                        Modifier
                            .padding(cornerRadius_8)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        DialogButton(
                            onClick = { onEvent.invoke(ConvertContract.Intent.OpenHome) },
                            backgroundColor = ColorBorderGray,
                            text = stringResource(R.string.bekor)
                        )
                        DialogButton(onClick = {
                            if (fromCurrency == toCurrency && fromWallet == toWallet) {
                                dialogAlert = true
                            } else {
                                if (amountTransaction.trim().isNotEmpty() &&
                                    (fromWalletOwner?.currencyBalance
                                        ?: 0.0) >= amountTransaction.trim().toDouble()
                                ) {
                                    if ((fromCurrency.id != toCurrency.id && currencyRate.trim()
                                            .isNotEmpty()) || fromCurrency.id == toCurrency.id
                                    ) {
                                        isErrorAmount = false
                                        confirmDialogState = true
                                        isErrorRate = false
                                    } else {
                                        isErrorRate = true
                                    }
                                } else {
                                    isErrorAmount = true
                                }
                            }
                        })
                    }
                }
            }
        }
    }
}