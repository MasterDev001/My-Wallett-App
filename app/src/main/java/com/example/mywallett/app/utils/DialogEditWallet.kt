package com.example.mywallett.app.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.a_common.data.WalletData
import com.example.mywallett.R
import com.example.mywallett.ui.theme.ColorBorderGray

@Composable
fun DialogEditWallet(
    walletData: WalletData,
    onDismissRequest: () -> Unit,
    onAddClick: (WalletData) -> Unit
) {

    var isErrorName by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf(walletData.name) }

    Dialog(onDismissRequest = onDismissRequest) {
        Card(Modifier.padding(padding_10), shape = RoundedCornerShape(padding_10)) {
            Column(
                Modifier.padding(horizontalPadding_16),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.hamyonni_tahrirlash),
                    modifier = Modifier.padding(8.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = textSize_16sp
                )
                OutlinedTextField(
                    value = name,
                    onValueChange = {
                        if (name != "") isErrorName = false
                        name = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    shape = RoundedCornerShape(cornerRadius_8),
                    isError = isErrorName,
                    label = { Text(text = stringResource(R.string.hamyon_nomi)) }
                )
                Row(
                    Modifier
                        .padding(cornerRadius_8)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    DialogButton(
                        onClick = onDismissRequest,
                        backgroundColor = ColorBorderGray,
                        text = stringResource(R.string.bekor)
                    )
                    DialogButton(onClick = {
                        if (name.length > 1) {
                            onAddClick(walletData.copy(name = name))
                            onDismissRequest()
                        } else {
                            isErrorName = true
                        }
                    })
                }
            }
        }
    }
}