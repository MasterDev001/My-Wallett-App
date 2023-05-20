package com.example.mywallett.app.screens.currencies

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import com.example.mywallett.R
import com.example.mywallett.app.screens.utils.horizontalPadding_16
import com.example.mywallett.app.screens.utils.padding_10
import com.example.mywallett.app.screens.utils.primaryCornerRadius_12
import com.example.mywallett.ui.theme.ColorGreenText
import com.example.presenter.currency.CurrencyViewModel
import uz.gita.vogayerlib.hiltScreenModel

class CurrencyScreen : AndroidScreen() {

    @Composable
    override fun Content() {
        val viewModel: CurrencyViewModel = hiltScreenModel()
        CurrencyContent()
    }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    fun CurrencyContent() {

        val itemList = remember { mutableStateListOf("dsahugh", "sahyiguh", "uighi") }


        Scaffold(topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.valyutalar)) },
                navigationIcon = {
                    IconButton(onClick = {

                    }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }, backgroundColor = Color.Red
            )
        }) {
            Column(
                Modifier
                    .background(Color.Gray)
                    .padding(horizontal = horizontalPadding_16)
                    .fillMaxSize(),
            ) {
                LazyColumn {
                    items(count = itemList.count(), itemContent = {
                        val item = itemList[it]
                        Card(
                            Modifier
                                .padding(top = horizontalPadding_16)
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(primaryCornerRadius_12)
                        ) {
                            Row(
                                Modifier
                                    .padding(padding_10)
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column(
                                    Modifier.fillMaxHeight(),
                                    verticalArrangement = Arrangement.SpaceEvenly
                                ) {
                                    Text(
                                        text = item,
                                        fontWeight = FontWeight.Bold,
                                        style = MaterialTheme.typography.h5
                                    )
                                    Text(
                                        text = "1$ = 25354",
                                        style = MaterialTheme.typography.subtitle1
                                    )

                                }
                                IconButton(onClick = {

                                }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.menu),
                                        contentDescription = ""
                                    )
                                }
                            }
                        }
                    })
                }
                Card(
                    Modifier
                        .padding(top = horizontalPadding_16)
                        .fillMaxWidth()
                        .clickable {

                        },
                    shape = RoundedCornerShape(primaryCornerRadius_12)
                ) {
                    Row(
                        Modifier
                            .padding(vertical = padding_10)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.plus),
                            contentDescription = "Add",
                        )
                        Text(
                            text = stringResource(R.string.yangi_valuta_qo_shish),
                            style = MaterialTheme.typography.h6,
                            color = ColorGreenText
                        )
                    }
                }
            }
        }
    }
}
