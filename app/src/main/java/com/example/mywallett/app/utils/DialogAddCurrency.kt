package com.example.mywallett.app.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.mywallett.R
import com.example.mywallett.ui.theme.ColorBorderGray

@Composable
fun DialogAddCurrency(
    currencyName: String,
    currencyRate: String,
    currencyNameOnValueChange: (String) -> Unit,
    currencyRateOnValueChange: (String) -> Unit,
    onDismissRequest: () -> Unit,
    onAddClick: () -> Unit
) {

    var isErrorName by remember { mutableStateOf(false) }
    var isErrorRate by remember { mutableStateOf(false) }

    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    ) {
        Card(Modifier.padding(8.dp), shape = RoundedCornerShape(padding_10)) {
            Column(
                Modifier.padding(horizontalPadding_16),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.padding(cornerRadius_8),
                    text = stringResource(id = R.string.yangi_valuta_qo_shish),
                    fontWeight = FontWeight.Bold,
                    fontSize = textSize_16sp
                )
                OutlinedTextField(
                    value = currencyName,
                    onValueChange = {
                        currencyNameOnValueChange(it)
                        if (currencyName != "") isErrorName = false
                    },
                    label = {
                        Text(text = stringResource(R.string.valyuta_nomi))
                    },
                    isError = isErrorName,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    shape = RoundedCornerShape(cornerRadius_8),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                )
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = stringResource(R.string.kurs_1))

                    OutlinedTextField(
                        modifier = Modifier.padding(5.dp),
                        value = currencyRate,
                        maxLines = 1,
                        onValueChange = {
                            if (currencyRate.toFloatOrNull() != null) isErrorRate = false
                            currencyRateOnValueChange(it)
                        },
                        label = {
                            Text(text = stringResource(R.string.qiymati))
                        },
                        shape = RoundedCornerShape(cornerRadius_8),
                        isError = isErrorRate,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Decimal, imeAction = ImeAction.Done
                        )
                    )
                }
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
                        if (currencyName != "" && currencyRate != "" && currencyRate.toFloatOrNull() != null) {
                            onAddClick()
                        } else if (currencyName == "") {
                            isErrorName = true
                        } else {
                            isErrorRate = true
                        }
                    })

                }
            }

        }
    }
}