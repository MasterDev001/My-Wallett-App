package com.example.mywallett.app.screens.home

import android.annotation.SuppressLint
import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.androidx.AndroidScreen
import com.example.mywallett.R
import com.example.mywallett.app.screens.utils.CircularButton
import com.example.mywallett.app.screens.utils.horizontalPadding_16
import com.example.mywallett.app.screens.utils.padding_10
import com.example.mywallett.app.screens.utils.textSize_21sp
import kotlinx.coroutines.launch

class HomeScreen : AndroidScreen() {

    @Composable
    override fun Content() {
        HomeScreenContent()
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreenContent() {
    var selectedItem by remember { mutableStateOf(0) }
    val scaffoldState =
        rememberScaffoldState(rememberDrawerState(initialValue = DrawerValue.Closed))
    val scope = rememberCoroutineScope()

    Scaffold(scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(title = {},
                navigationIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.menu),
                        contentDescription = "Menu",
                        modifier = Modifier.clickable {
                            scope.launch { scaffoldState.drawerState.open() }
                        }
                    )
                })
        }, content = {
            if (selectedItem == 0) {
                HomeCScreen()
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
fun HomeCScreen() {
    val itemList = remember { mutableStateListOf("fsadjhju", "akhsfk", "daskhjg", "lksajhf") }

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
                    Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "* * *",
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

                }
                CircularButton(
                    stringResource(R.string.chiqim), icon = R.drawable.chiqim
                ) {

                }
                CircularButton(
                    stringResource(R.string.qarz_olish), icon = R.drawable.qarz_olish
                ) {

                }
                CircularButton(
                    stringResource(R.string.qarz_berish), icon = R.drawable.qarz_berish
                ) {

                }
            }
            Row(
                Modifier
                    .padding(top = horizontalPadding_16)
                    .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
            ) {

                CircularButton(
                    "Qarzdorlar", icon = R.drawable.qarzdorlar
                ) {

                }
                CircularButton(
                    "Haqdorlar", icon = R.drawable.haqdorlar
                ) {

                }
                CircularButton(
                    "Hamyonlar", icon = R.drawable.wallet
                ) {

                }
                CircularButton(
                    stringResource(R.string.valyutalar), icon = R.drawable.currency
                ) {
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
                    fontWeight = FontWeight.Bold
                )
            }
            LazyColumn {
                items(itemList.count(), itemContent = {
                    val item = itemList[it]
                    Row(
                        Modifier
                            .padding(vertical = padding_10)
                            .fillMaxWidth()
                            .clickable {

                            }) {
                        Image(
                            contentScale = ContentScale.FillBounds,
                            painter = painterResource(id = R.drawable.kirim),
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(MaterialTheme.colors.primary),
                            contentDescription = ""
                        )
                        Row(
                            Modifier
                                .padding(start = padding_10)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = "Payment",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = textSize_21sp
                                )
                                Text(text = "25.05.2023", style = MaterialTheme.typography.body2)
                            }
                            Text(text = "$4546546", fontWeight = FontWeight.Bold)
                        }
                    }
                })
            }
        }
    }
}

@Composable
fun Settings() {

}