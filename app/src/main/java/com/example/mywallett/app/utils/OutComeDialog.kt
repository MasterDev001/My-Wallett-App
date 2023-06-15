package com.example.mywallett.app.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.mywallett.R
import com.example.mywallett.ui.theme.ColorBorderGray

@Composable
fun OutComeDialog(
    currencyName: String,
    currencyBalance: Double,
    walletName: String,
    onDismiss: () -> Unit,
    onConfirmClick: (Double, String) -> Unit
) {

    var amount by remember { mutableStateOf("") }
    var comment by remember { mutableStateOf("") }
    var isErrorAmount by remember { mutableStateOf(false) }
    var wrongAmount by remember { mutableStateOf(false) }

    Dialog(
        onDismissRequest = onDismiss, properties = DialogProperties(
            dismissOnBackPress = false, dismissOnClickOutside = false
        )
    ) {
        Card(
            shape = RoundedCornerShape(10.dp),
        ) {
            Column(
                modifier = Modifier
                    .padding(cornerRadius_8)
                    .clip(RoundedCornerShape(10.dp)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "${walletName} hamyonda\n${currencyBalance} $currencyName bor. Qancha chiqim qilmoqchisiz?",
                    modifier = Modifier.padding(8.dp),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    value = amount,
                    onValueChange = {
                        if (amount.toFloatOrNull() != null) isErrorAmount = false
                        amount = it
                    },
                    label = {
                        Text(
                            text = stringResource(id = R.string.summani_kiriting),
                            color = Color.Unspecified
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal, imeAction = ImeAction.Done
                    ),
                    isError = isErrorAmount,
                    shape = RoundedCornerShape(cornerRadius_8),
                )
//
                Text(
                    text = if (wrongAmount) stringResource(R.string.hisobingizda_yetarli_mablag_mavjud_emas) else "",
                    fontSize = 14.sp,
                    color = Color.Red,
                )
//
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 100.dp, max = 160.dp)
                        .padding(5.dp),
                    value = comment,
                    onValueChange = {
                        comment = it
                    },
                    label = {
                        Text(text = stringResource(id = R.string.izoh_ixtiyoriy))
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text, imeAction = ImeAction.Done
                    ),
                    shape = RoundedCornerShape(cornerRadius_8),
                )

                Row(
                    Modifier
                        .padding(cornerRadius_8)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    DialogButton(
                        onClick = onDismiss,
                        backgroundColor = ColorBorderGray,
                        text = stringResource(R.string.bekor)
                    )
                    DialogButton(onClick = {
                        if (amount.trim()
                                .isNotEmpty() && amount.toFloatOrNull() != null && currencyBalance >= amount.trim()
                                .toDouble()
                        ) {
                            onConfirmClick(amount.trim().toDouble(), comment)
                            onDismiss()
                        } else if (currencyBalance < amount.trim().toDouble()) {
                            wrongAmount = true
                        } else {
                            isErrorAmount = true
                        }
                    })
                }
            }
        }
    }
}
