package com.example.mywallett.app.utils

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.a_common.data.CurrencyData
import com.example.a_common.data.WalletData
import com.example.mywallett.R
import com.example.mywallett.ui.theme.ColorBorderGray

@Composable
fun DialogInCome(
    wallet: WalletData,
    currencyList:List<CurrencyData>,
    onDismiss: () -> Unit,
    onAddClick: (Double, String, CurrencyData) -> Unit
) {

    var amountTransaction by rememberSaveable { mutableStateOf("") }
    var isErrorAmount by remember { mutableStateOf(false) }
    var dropDownState by remember { mutableStateOf(false) }
    var comment by remember { mutableStateOf("") }
    var selectedCurrency by remember { mutableStateOf(currencyList[1]) }

    Dialog(
        onDismissRequest = { onDismiss() }, properties = DialogProperties(
            dismissOnBackPress = false, dismissOnClickOutside = false
        )
    ) {
        Card(Modifier.padding(8.dp), shape = RoundedCornerShape(padding_10)) {
            Column(
                Modifier.padding(horizontalPadding_16),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "${wallet.name} ga qancha pul kiritmoqchisiz?",
                    modifier = Modifier.padding(8.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = textSize_16sp
                )
                OutlinedTextField(value = amountTransaction,
                    onValueChange = {
                        if (amountTransaction != "") isErrorAmount = false
                        amountTransaction = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    shape = RoundedCornerShape(cornerRadius_8),
                    isError = isErrorAmount,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal, imeAction = ImeAction.Done
                    ),
                    label = { Text(text = stringResource(R.string.summani_kiriting)) }
                )
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = stringResource(R.string.valyuta))
                    Row(
                        Modifier
                            .padding(5.dp)
                            .width(150.dp)
                            .clickable {
                                dropDownState = !dropDownState
                            }
                            .border(
                                width = 2.dp,
                                color = Color.Gray,
                                shape = RoundedCornerShape(16.dp)
                            )
                            .padding(horizontal = horizontalPadding_16, vertical = 5.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = selectedCurrency.name)
                        Icon(
                            painter = painterResource(id = R.drawable.more),
                            contentDescription = "more"
                        )
                        DropdownMenu(
                            expanded = dropDownState,
                            modifier = Modifier.heightIn(min = 0.dp, max = 200.dp),
                            onDismissRequest = { dropDownState = false }) {
                            for (currency in currencyList) {
                                DropdownMenuItem(onClick = {
                                    selectedCurrency = currency
                                    dropDownState = false
                                }) {
                                    Text(text = currency.name)
                                }
                            }
                        }
                    }
                }
                OutlinedTextField(value = comment,
                    onValueChange = { comment = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 100.dp, max = 200.dp)
                        .padding(5.dp),
                    shape = RoundedCornerShape(cornerRadius_8),
                    label = { Text(text = stringResource(R.string.izoh_ixtiyoriy)) }
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
                        if (amountTransaction.isNotEmpty()) {
                            onAddClick(amountTransaction.toDouble(), comment, selectedCurrency)

                            onDismiss()
                        } else {
                            isErrorAmount = true
                        }
                    })
                }
            }
        }
    }
}