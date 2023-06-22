package com.example.mywallett.app.screens.wallets

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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import cafe.adriel.voyager.androidx.AndroidScreen
import com.example.a_common.data.WalletData
import com.example.mywallett.R
import com.example.mywallett.app.utils.DialogAddWallet
import com.example.mywallett.app.utils.DialogConfirm
import com.example.mywallett.app.utils.DialogEditWallet
import com.example.mywallett.app.utils.PopUpToDialog
import com.example.mywallett.app.utils.WalletItem
import com.example.mywallett.app.utils.horizontalPadding_16
import com.example.mywallett.ui.theme.ColorGreenButton
import com.example.presenter.wallets.WalletsContract
import com.example.presenter.wallets.WalletsViewModel
import uz.gita.vogayerlib.hiltScreenModel
import java.util.UUID

class WalletsScreen : AndroidScreen() {

    @Composable
    override fun Content() {
        val viewModel: WalletsViewModel = hiltScreenModel()
        val uiState = viewModel.uiState.collectAsState()
        WalletScreenContent(uiState, viewModel, viewModel::onEventDispatcher)
    }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    private fun WalletScreenContent(
        uiState: State<WalletsContract.UiState>,
        viewModel: WalletsViewModel,
        onEvent: (WalletsContract.Intent) -> Unit
    ) {

        val popUpState = remember { mutableStateOf(false) }
        var offsetPopUp by remember { mutableStateOf(Offset(0f, 0f)) }
        val walletsList by viewModel.wallets.collectAsState(initial = emptyList())
        var addWalletDialogState by remember { mutableStateOf(false) }
        var walletCurrent by remember { mutableStateOf(WalletData("")) }
        val editDialogState = remember { mutableStateOf(false) }
        val deleteDialogState = remember { mutableStateOf(false) }

        if (addWalletDialogState) {
            DialogAddWallet(
                headerText = stringResource(R.string.yangi_hamyon_qo_shish),
                viewModel::isWalletExist,
                onDismissRequest = { addWalletDialogState = false }
            ) { walletName ->
                onEvent.invoke(
                    WalletsContract.Intent.AddWallet(
                        WalletData(
                            UUID.randomUUID().toString(),
                            walletName,
                            date = System.currentTimeMillis()
                        )
                    )
                )
                addWalletDialogState = false
            }
        }

        if (editDialogState.value) {
            popUpState.value = false
            DialogEditWallet(walletCurrent,
                onDismissRequest = { editDialogState.value = false },
                onAddClick = { newWallet ->
                    onEvent.invoke(WalletsContract.Intent.UpdateWallet(newWallet))
                })
        }

        if (deleteDialogState.value) {
            popUpState.value = false
            DialogConfirm(
                text = walletCurrent.name,
                message = walletCurrent.name + stringResource(R.string.ni_o_chirishga_ishonchingiz_komilmi),
                onDismiss = {
                    deleteDialogState.value = false
                },
                onConfirm = {
                    onEvent.invoke(WalletsContract.Intent.DeleteWallet(walletCurrent))
                })
        }

        if (popUpState.value) {
            PopUpToDialog(offset = offsetPopUp,
                text = walletCurrent.name,
                onDeleteClick = {
                    deleteDialogState.value = true
                },
                onEditClick = {
                    editDialogState.value = true
                },
                onDismissRequest = { popUpState.value = false })
        }


        Scaffold(topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.hamyonlar)) },
                navigationIcon = {
                    IconButton(onClick = {
                        onEvent.invoke(WalletsContract.Intent.OpenHome)
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
                LazyColumn {
                    items(count = walletsList.count(), itemContent = {
                        val item = walletsList[it]
                        WalletItem(item, viewModel,offsetPopUp, onItemClick = {

                        }) { offset, state ->
                            offsetPopUp = offset
                            popUpState.value = state
                            walletCurrent = walletsList[it]
                        }
                    })
                }
            }
        }
    }
}