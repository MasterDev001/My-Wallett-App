package com.example.mywallett.app.screens.outcome

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import cafe.adriel.voyager.androidx.AndroidScreen
import com.example.mywallett.R
import com.example.mywallett.app.utils.WalletItemWithoutMenu
import com.example.mywallett.app.utils.horizontalPadding_16
import com.example.presenter.outcome.OutComeContract
import com.example.presenter.outcome.OutComeViewModel
import uz.gita.vogayerlib.hiltScreenModel

class OutComeScreen : AndroidScreen() {

    @Composable
    override fun Content() {
        val viewModel: OutComeViewModel = hiltScreenModel()
        val uiState = viewModel.uiState.collectAsState()
        OutComeContent(uiState, viewModel, viewModel::onEventDispatcher)
    }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    private fun OutComeContent(
        uiState: State<OutComeContract.UiState>,
        viewModel: OutComeViewModel,
        onEvent: (OutComeContract.Intent) -> Unit
    ) {

        val walletList by viewModel.wallets.collectAsState(initial = emptyList())

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = stringResource(id = R.string.chiqim)) },
                    navigationIcon = {
                        IconButton(onClick = {
                            onEvent.invoke(OutComeContract.Intent.OpenHome)
                        }) {
                            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back")
                        }
                    })
            }
        ) {
            Column(
                Modifier
                    .background(Color.Gray)
                    .padding(horizontal = horizontalPadding_16)
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
                            walletOwnerListById, getCurrency = { currencyId ->
                                viewModel.getCurrency(currencyId)
                            }
                        ) {
                            onEvent.invoke(OutComeContract.Intent.OpenOutComeCurrencies(item))
                        }
                    }
                }
            }
        }
    }
}