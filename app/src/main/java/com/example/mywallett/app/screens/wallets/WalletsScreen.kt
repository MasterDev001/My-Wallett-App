package com.example.mywallett.app.screens.wallets

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import cafe.adriel.voyager.androidx.AndroidScreen
import com.example.mywallett.R
import com.example.mywallett.app.utils.DialogAddWallet
import com.example.mywallett.app.utils.horizontalPadding_16
import com.example.mywallett.ui.theme.ColorGreenButton

class WalletsScreen : AndroidScreen() {

    @Composable
    override fun Content() {
        WalletScreenContent()
    }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    private fun WalletScreenContent() {

        var addWalletDialogState by remember { mutableStateOf(false) }
        var walletName by remember { mutableStateOf("") }

        if (addWalletDialogState) {
            DialogAddWallet(
                headerText = stringResource(R.string.yangi_hamyon_qo_shish),
                name = walletName,
                onNameChange = { walletName = it },
                onDismissRequest = { addWalletDialogState = false },
                onAddClick = {

                })
        }

        Scaffold(topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.hamyonlar)) },
                navigationIcon = {
                    IconButton(onClick = {

                    }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back")
                    }
                })
        }, floatingActionButton = {
            FloatingActionButton(onClick = {
                addWalletDialogState = true
            }, backgroundColor = ColorGreenButton) {
                Image(
                    painter = painterResource(id = R.drawable.add_white),
                    contentDescription = "add",
                )
            }
        }) {
            Column(
                Modifier
                    .background(Color.Gray)
                    .padding(horizontal = horizontalPadding_16)
                    .fillMaxSize(),
            ) {
//                LazyColumn {
//                    items(count =, itemContent = {
//                        val item =
//                    })
//                }
            }
        }
    }
}