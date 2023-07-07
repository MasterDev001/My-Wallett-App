package com.example.mywallett.app.screens.home

import android.annotation.SuppressLint
import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.androidx.AndroidScreen
import com.example.mywallett.R
import com.example.mywallett.app.utils.CircularButton
import com.example.mywallett.app.utils.HistoryItem
import com.example.mywallett.app.utils.horizontalPadding_16
import com.example.mywallett.app.utils.textSize_21sp
import com.example.presenter.home.HomeContract
import com.example.presenter.home.HomeViewModel
import kotlinx.coroutines.launch
import uz.gita.vogayerlib.hiltScreenModel

class HomeScreen : AndroidScreen() {

    @Composable
    override fun Content() {
        val viewModel: HomeViewModel = hiltScreenModel()
        val uiState = viewModel.uiState.collectAsState()
        HomeScreenContent(uiState, viewModel::onEventDispatcher, viewModel)
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun HomeScreenContent(
    uiState: State<HomeContract.UiState>,
    onEvent: (HomeContract.Intent) -> Unit,
    viewModel: HomeViewModel
) {
    var selectedItem by remember { mutableStateOf(0) }
    val scaffoldState =
        rememberScaffoldState(rememberDrawerState(initialValue = DrawerValue.Closed))
    val scope = rememberCoroutineScope()

    Scaffold(scaffoldState = scaffoldState, topBar = {
        TopAppBar(title = {}, navigationIcon = {
            Icon(painter = painterResource(id = R.drawable.menu),
                contentDescription = "Menu",
                modifier = Modifier.clickable {
                    scope.launch { scaffoldState.drawerState.open() }
                })
        })
    }, content = {
        if (selectedItem == 0) {
            HomeCScreen(uiState, onEvent, viewModel)
        } else {
            Settings()
        }
    }, drawerContent = {
        Column(
            Modifier
                .fillMaxWidth()
                .size(150.dp)
                .background(colorResource(id = R.color.black))
        ) {
            Text(text = "asugyhi", fontSize = 36.sp, color = Color.White)
        }

        DropdownMenuItem(onClick = {
            selectedItem = 0
            scope.launch { scaffoldState.drawerState.close() }
        }) {
            Text(text = "Home")
        }
        DropdownMenuItem(onClick = {
            selectedItem = 1
            scope.launch { scaffoldState.drawerState.close() }
        }) {
            Text(text = "Second Screen")
        }
        val activity = (LocalContext.current as Activity)
        BackHandler {
            if (scaffoldState.drawerState.isOpen) {
                scope.launch { scaffoldState.drawerState.close() }
            } else {
                activity.finish()
            }
        }
    })
}

@Composable
private fun HomeCScreen(
    uiState: State<HomeContract.UiState>,
    onEvent: (HomeContract.Intent) -> Unit,
    viewModel: HomeViewModel
) {
    val historyList by viewModel.getLimitedHistory(5).collectAsState(initial = emptyList())

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Card(
            Modifier
                .padding(horizontalPadding_16)
                .fillMaxWidth(),
            backgroundColor = MaterialTheme.colors.primary,
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontalPadding_16),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "USD", style = MaterialTheme.typography.h4)
                    Text(
                        "13245",
                        modifier = Modifier.padding(start = horizontalPadding_16),
                        style = MaterialTheme.typography.h5
                    )
                }
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "UZS", style = MaterialTheme.typography.h4)
                    Text(
                        "13245",
                        modifier = Modifier.padding(start = horizontalPadding_16),
                        style = MaterialTheme.typography.h5
                    )

                }
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "TL", style = MaterialTheme.typography.h4)
                    Text(
                        "13245",
                        modifier = Modifier.padding(start = horizontalPadding_16),
                        style = MaterialTheme.typography.h5
                    )

                }
                Column(
                    Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "* * *",
                        style = MaterialTheme.typography.h5,
                        modifier = Modifier.clickable { })
                }
            }
        }
        Column(
            Modifier
                .padding(horizontalPadding_16)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                CircularButton(
                    stringResource(R.string.kirim), icon = R.drawable.kirim
                ) {
                    onEvent.invoke(HomeContract.Intent.OpenInCome)
                }
                CircularButton(
                    stringResource(R.string.chiqim), icon = R.drawable.chiqim
                ) {
                    onEvent.invoke(HomeContract.Intent.OpenOutCome)
                }
                CircularButton(
                    stringResource(R.string.qarz_olish), icon = R.drawable.qarz_olish
                ) {
                    onEvent.invoke(
                        HomeContract.Intent.OpenBorrow(
                            viewModel.persons(),
                            viewModel.currencies(),
                            viewModel.wallets()
                        )
                    )
                }
                CircularButton(
                    stringResource(R.string.qarz_berish), icon = R.drawable.qarz_berish
                ) {
                    onEvent.invoke(
                        HomeContract.Intent.OpenLend(
                            viewModel.persons(),
                            viewModel.currencies(),
                            viewModel.wallets()
                        )
                    )
                }
            }
            Row(
                Modifier
                    .padding(top = horizontalPadding_16)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                CircularButton(
                    stringResource(R.string.shaxslar), icon = R.drawable.persons
                ) {
                    onEvent.invoke(HomeContract.Intent.OpenPersons)
                }
                CircularButton(
                    "Ayriboshlash", icon = R.drawable.convert
                ) {
                    onEvent.invoke(
                        HomeContract.Intent.OpenConvert(
                            viewModel.currencies(),
                            viewModel.wallets()
                        )
                    )
                }
                CircularButton(
                    stringResource(R.string.hamyonlar), icon = R.drawable.wallet
                ) {
                    onEvent.invoke(HomeContract.Intent.OpenWallets)
                }
                CircularButton(
                    stringResource(R.string.valyutalar), icon = R.drawable.currency
                ) {
                    onEvent.invoke(HomeContract.Intent.OpenCurrency)
                }
            }
        }
        Column(
            Modifier
                .padding(horizontal = horizontalPadding_16)
                .fillMaxSize(),
        ) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {

                Text(
                    text = stringResource(R.string.recent_transactions),
                    fontSize = textSize_21sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = stringResource(R.string.ko_proq),
                    fontSize = textSize_21sp,
                    fontWeight = FontWeight.Bold, modifier = Modifier.clickable {
                        onEvent.invoke(HomeContract.Intent.OpenHistory)
                    }
                )
            }
            LazyColumn {
                items(historyList.count(), itemContent = {
                    val item = historyList[it]
                    HistoryItem(item = item) {

                    }

                })
            }
        }
    }
}

@Composable
fun Settings() {

}