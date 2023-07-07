package com.example.mywallett.app.screens.persons.personHistory

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.paging.compose.collectAsLazyPagingItems
import cafe.adriel.voyager.androidx.AndroidScreen
import com.example.a_common.data.PersonData
import com.example.mywallett.R
import com.example.mywallett.app.utils.CurrencyFlowRowItem
import com.example.mywallett.app.utils.HistoryItem
import com.example.presenter.persons.personHistory.PersonHistoryContract
import com.example.presenter.persons.personHistory.PersonHistoryViewModel
import uz.gita.vogayerlib.hiltScreenModel

class PersonHistoryScreen(val personData: PersonData) : AndroidScreen() {

    @Composable
    override fun Content() {
        val viewModel: PersonHistoryViewModel = hiltScreenModel()
        val uiState = viewModel.uiState.collectAsState()
        PersonHistoryContent(uiState, viewModel, viewModel::onEventDispatcher)
    }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    private fun PersonHistoryContent(
        uiState: State<PersonHistoryContract.UiState>,
        viewModel: PersonHistoryViewModel,
        onEvent: (PersonHistoryContract.Intent) -> Unit
    ) {

        val historyList = viewModel.personHistoryPager(personData.id).collectAsLazyPagingItems()
        val personCurrencyList by viewModel.getPersonCurriesByPersonId(personData.id)
            .collectAsState(initial = emptyList())

        val debts = personCurrencyList.filter { it.currencyBalance > 0 }
        val lends = personCurrencyList.filter { it.currencyBalance < 0 }

        Scaffold(topBar = {
            TopAppBar(title = {
                Icon(
                    painter = painterResource(id = R.drawable.persons),
                    contentDescription = ""
                )
                Text(text = personData.name)
            },
                navigationIcon = {
                    IconButton(onClick = {
                        onEvent.invoke(PersonHistoryContract.Intent.OpenHome)
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
                    Text(text = stringResource(R.string.qarzlari), fontWeight = FontWeight.Bold)
                    CurrencyFlowRowItem(
                        personCurrencyList = debts,
                        getCurrency = viewModel::getCurrency
                    )
                }
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(text = stringResource(R.string.haqlari), fontWeight = FontWeight.Bold)
                    CurrencyFlowRowItem(
                        personCurrencyList = lends,
                        getCurrency = viewModel::getCurrency
                    )
                }
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = stringResource(R.string.tel_raqami), fontWeight = FontWeight.Bold)
                    Text(
                        text = if (personData.phoneNumber == "") "kiritilmagan" else personData.phoneNumber,
                    )
                }
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = stringResource(R.string.manzili), fontWeight = FontWeight.Bold)
                    Text(
                        text = if (personData.address == "") "kiritilmagan" else personData.address,
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