package com.example.mywallett.app.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.mywallett.R
import com.example.mywallett.ui.theme.ColorBorderGray

@Composable
fun DialogPerson(
    onDismissRequest: () -> Unit,
    isPersonExist: (String) -> Boolean,
    namePlaceholder:String="",
    phonePlaceholder:String="",
    addressPlaceholder:String="",
    onAddClick: (String, String, String) -> Unit
) {

    var isErrorName by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf(namePlaceholder) }
    var phoneNumber by remember { mutableStateOf(phonePlaceholder) }
    var address by remember { mutableStateOf(addressPlaceholder) }
    val scrollState = rememberScrollState()

    Dialog(onDismissRequest = onDismissRequest) {
        Card(Modifier.padding(padding_10), shape = RoundedCornerShape(padding_10)) {
            Column(
                Modifier
                    .padding(horizontalPadding_16)
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = stringResource(R.string.yangi_odam_qo_shish),
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
                    label = { Text(text = stringResource(R.string.isim)) }
                )
                OutlinedTextField(
                    value = phoneNumber,
                    onValueChange = { phoneNumber = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    shape = RoundedCornerShape(cornerRadius_8),
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    label = { Text(text = stringResource(R.string.tel_raqam_ixtiyoriy)) }
                )
                OutlinedTextField(
                    value = address,
                    onValueChange = { address = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    shape = RoundedCornerShape(cornerRadius_8),
                    label = { Text(text = "Manzil (ixtiyoriy)") }
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
                        if (name.length > 1 && !isPersonExist.invoke(name.trim())) {
                            onAddClick(name.trim(), phoneNumber.trim(), address.trim())
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