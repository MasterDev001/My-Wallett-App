package com.example.mywallett.app.screens.history

import android.annotation.SuppressLint
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
import androidx.compose.ui.res.stringResource
import androidx.paging.compose.collectAsLazyPagingItems
import cafe.adriel.voyager.androidx.AndroidScreen
import com.example.mywallett.R
import com.example.mywallett.app.utils.HistoryItem
import com.example.presenter.history.HistoryContract
import com.example.presenter.history.HistoryViewModel
import uz.gita.vogayerlib.hiltScreenModel

class HistoryScreen : AndroidScreen() {

    @Composable
    override fun Content() {
        val viewModel: HistoryViewModel = hiltScreenModel()
        val uiState = viewModel.uiState.collectAsState()
        HistoryScreenContent(uiState, viewModel, viewModel::onEventDispatcher)
    }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    private fun HistoryScreenContent(
        uiState: State<HistoryContract.UiState>,
        viewModel: HistoryViewModel,
        onEvent: (HistoryContract.Intent) -> Unit
    ) {

        val historyList = viewModel.historyPager.collectAsLazyPagingItems()

        Scaffold(topBar =
        {
            TopAppBar(title = { Text(text = stringResource(R.string.tarix)) },
                navigationIcon = {
                    IconButton(onClick = {
                        onEvent.invoke(HistoryContract.Intent.OpenHome)
                    }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back")
                    }
                })
        }) {
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
