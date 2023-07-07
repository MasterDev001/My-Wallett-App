package com.example.mywallett.app.screens.wallets.walletHistory

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.a_common.data.WalletData
import com.example.mywallett.R
import com.example.mywallett.app.utils.HistoryItem
import com.example.mywallett.app.utils.WalletFlowRowItem
import com.example.mywallett.navigation.AppScreen
import com.example.presenter.wallets.walletHistory.WalletHistoryContract
import com.example.presenter.wallets.walletHistory.WalletHistoryViewModel
import uz.gita.vogayerlib.hiltScreenModel

class WalletHistoryScreen(private val walletData: WalletData) : AppScreen() {

    @Composable
    override fun Content() {
        val viewModel: WalletHistoryViewModel = hiltScreenModel()
        val uiState = viewModel.uiState.collectAsState()
        WalletHistoryContent(uiState, viewModel, viewModel::onEventDispatcher)
    }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    private fun WalletHistoryContent(
        uiState: State<WalletHistoryContract.UiState>,
        viewModel: WalletHistoryViewModel,
        onEvent: (WalletHistoryContract.Intent) -> Unit
    ) {

        val walletOwnerList by viewModel.getWalletOwnerListByWalletId(walletData.id).collectAsState(
            initial = emptyList()
        )
        val historyList = viewModel.walletHistoryPager(walletData.id).collectAsLazyPagingItems()

        Scaffold(topBar = {
            TopAppBar(title = {
                Icon(
                    painter = painterResource(id = R.drawable.wallet), contentDescription = ""
                )
                Text(text = walletData.name)
            }, navigationIcon = {
                IconButton(onClick = {
                    onEvent.invoke(WalletHistoryContract.Intent.OpenHome)
                }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back")
                }
            })
        }) {
            Column(Modifier.fillMaxSize()) {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(text = stringResource(R.string.balans) + ":", fontWeight = FontWeight.Bold)
                    WalletFlowRowItem(
                        walletOwnerListById = walletOwnerList, getCurrency = viewModel::getCurrency
                    )
                }
                Text(text = stringResource(R.string.tarix) + ":")

                LazyColumn {
                    items(count = historyList.itemCount) {
                        val item = historyList[it]
                        HistoryItem(item = item!!) {

                        }
                    }
                }
            }
        }
    }
}