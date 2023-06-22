package com.example.mywallett.app.screens.borrow

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import com.example.a_common.data.CurrencyData
import com.example.a_common.data.PersonData
import com.example.a_common.data.WalletData
import com.example.mywallett.R
import com.example.mywallett.app.utils.CurrencyDropDown
import com.example.mywallett.app.utils.DialogButton
import com.example.mywallett.app.utils.DialogConfirm
import com.example.mywallett.app.utils.PersonDropDown
import com.example.mywallett.app.utils.BorrowWalletDropDown
import com.example.mywallett.app.utils.cornerRadius_8
import com.example.mywallett.app.utils.horizontalPadding_16
import com.example.mywallett.app.utils.textSize_26sp
import com.example.mywallett.ui.theme.ColorBorderGray
import com.example.presenter.borrow.BorrowContract
import com.example.presenter.borrow.BorrowViewModel
import uz.gita.vogayerlib.hiltScreenModel

class BorrowScreen(
    private val personsList: List<PersonData>,
    private val currenciesList: List<CurrencyData>,
    private val walletsList: List<WalletData>
) : AndroidScreen() {

    @Composable
    override fun Content() {
        val viewModel: BorrowViewModel = hiltScreenModel()
        val uiState = viewModel.uiState.collectAsState()
        BorrowScreenContent(uiState, viewModel, viewModel::onEventDispatcher)
    }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    private fun BorrowScreenContent(
        uiState: State<BorrowContract.UiState>,
        viewModel: BorrowViewModel,
        onEvent: (BorrowContract.Intent) -> Unit
    ) {
        var confirmDialogState by remember { mutableStateOf(false) }
        var amountTransaction by rememberSaveable { mutableStateOf("") }
        var isErrorAmount by remember { mutableStateOf(false) }
        var dropDownState by remember { mutableStateOf(false) }
        var comment by remember { mutableStateOf("") }
        var selectedCurrency by remember { mutableStateOf(CurrencyData("")) }
        var selectedWallet by remember { mutableStateOf(WalletData("")) }
        var selectedPerson by remember { mutableStateOf(PersonData("")) }

        if (confirmDialogState) {
            DialogConfirm(
                text = selectedPerson.name,
                message = "${selectedPerson.name} dan $amountTransaction ${selectedCurrency.name} qarz olishga ishonchingiz komilmi",
                onDismiss = { confirmDialogState = false },
                onConfirm = {
                    onEvent.invoke(
                        BorrowContract.Intent.BorrowMoney(
                            selectedPerson,
                            amountTransaction.trim(),
                            selectedCurrency,
                            selectedWallet,
                            comment
                        )
                    )
                })
        }

        Scaffold(topBar =
        {
            TopAppBar(title = { Text(text = stringResource(id = R.string.qarz_olish)) },
                navigationIcon = {
                    IconButton(onClick = {
                        onEvent.invoke(BorrowContract.Intent.OpenHome)
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
                        text = stringResource(R.string.kimdan_qarz_olmoqchisiz),
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                            .fillMaxWidth(),
                    )

                    PersonDropDown(
                        Modifier
                            .padding(5.dp)
                            .fillMaxWidth(),
                        list = personsList,
                        selectedPerson = { selectedPerson = it })

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
                            selectedCurrency = { selectedCurrency = it })
                    }

                    Text(
                        text = stringResource(R.string.qaysi_hamyonga),
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                            .fillMaxWidth(),
                    )
                    BorrowWalletDropDown(
                        Modifier
                            .padding(5.dp)
                            .fillMaxWidth(),
                        list = walletsList,
                        selectedWallet = { selectedWallet = it })

                    OutlinedTextField(value = comment,
                        onValueChange = { comment = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 100.dp, max = 200.dp)
                            .padding(5.dp),
                        shape = RoundedCornerShape(cornerRadius_8),
                        label = { Text(text = stringResource(R.string.izoh_ixtiyoriy)) }
                    )
                    Row(
                        Modifier
                            .padding(cornerRadius_8)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        DialogButton(
                            onClick = { onEvent.invoke(BorrowContract.Intent.OpenHome) },
                            backgroundColor = ColorBorderGray,
                            text = stringResource(R.string.bekor)
                        )
                        DialogButton(onClick = {
                            if (amountTransaction.trim().isNotEmpty()) {
                                confirmDialogState = true
                            } else {
                                isErrorAmount = true
                            }
                        })
                    }
                }
            }
        }
    }
}
