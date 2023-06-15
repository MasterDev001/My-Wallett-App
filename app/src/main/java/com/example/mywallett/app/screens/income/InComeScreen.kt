package com.example.mywallett.app.screens.income

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import cafe.adriel.voyager.androidx.AndroidScreen
import com.example.a_common.data.WalletData
import com.example.mywallett.R
import com.example.mywallett.app.utils.DialogAddWallet
import com.example.mywallett.app.utils.DialogInCome
import com.example.mywallett.app.utils.WalletItemWithoutMenu
import com.example.mywallett.app.utils.horizontalPadding_16
import com.example.mywallett.ui.theme.ColorGreenButton
import com.example.presenter.income.InComeContract
import com.example.presenter.income.InComeViewModel
import uz.gita.vogayerlib.hiltScreenModel
import java.util.UUID

class InComeScreen : AndroidScreen() {

    @Composable
    override fun Content() {
        val viewModel: InComeViewModel = hiltScreenModel()
        val uiState = viewModel.uiState.collectAsState()
        InComeContent(uiState, viewModel, viewModel::onEventDispatcher)
    }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    fun InComeContent(
        uiState: State<InComeContract.UiState>,
        viewModel: InComeViewModel,
        onEvent: (InComeContract.Intent) -> Unit
    ) {

        val walletList by viewModel.wallets.collectAsState(initial = emptyList())
        val currencyList by viewModel.currencies.collectAsState(initial = emptyList())
        var addWalletDialogState by remember { mutableStateOf(false) }
        var inComeDialogState by remember { mutableStateOf(false) }
        var currentWallet by remember { mutableStateOf(WalletData(id = "")) }

        if (addWalletDialogState) {
            DialogAddWallet(headerText = stringResource(R.string.yangi_hamyon_qo_shish),
                onDismissRequest = { addWalletDialogState = false },
                onAddClick = { walletName ->
                    onEvent.invoke(
                        InComeContract.Intent.AddWallet(
                            WalletData(
                                UUID.randomUUID().toString(),
                                walletName,
                                date = System.currentTimeMillis()
                            )
                        )
                    )
                    addWalletDialogState = false
                })
        }

        if (inComeDialogState) {
            DialogInCome(currentWallet, currencyList, onDismiss = {
                inComeDialogState = false
            }, onAddClick = { amount, comment, currency ->
                onEvent.invoke(
                    InComeContract.Intent.IncomeMoney(
                        amount,
                        comment,
                        currency,
                        currentWallet
                    )
                )
            })
        }

        Scaffold(topBar = {
            TopAppBar(title = { Text(text = stringResource(id = R.string.kirim)) },
                navigationIcon = {
                    IconButton(onClick = {
                        onEvent.invoke(InComeContract.Intent.OpenHome)
                    }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back")
                    }
                })
        }, floatingActionButton = {
            FloatingActionButton(onClick = {
                addWalletDialogState = true
            }, backgroundColor = ColorGreenButton) {
                Image(
                    painter = painterResource(id = R.drawable.add_white), contentDescription = "Add"
                )
            }
        }) {
            Column(
                Modifier
                    .background(Color.Gray)
                    .padding(horizontal=horizontalPadding_16)
                    .fillMaxSize()
            ) {
                LazyColumn {
                    items(count = walletList.count()) {
                        val item = walletList[it]
                        val walletOwnerListById =
                            viewModel.getWalletOwnerListByWalletId(item.id)
                                .collectAsState(initial = emptyList())

                        WalletItemWithoutMenu(
                            wallet = item,
                            walletOwnerListById,
                            getCurrency = { currencyId ->
                                viewModel.getCurrency(currencyId)
                            }) {
                            inComeDialogState = true
                            currentWallet = item
                        }
                    }
                }
            }
        }
    }
}