package com.example.mywallett.app.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.mywallett.R
import com.example.mywallett.ui.theme.ColorBorderGray

@Composable
fun DialogConfirm(text: String, message: String, onDismiss: () -> Unit, onConfirm: () -> Unit) {

    Dialog(onDismissRequest = onDismiss) {
        Card(shape = RoundedCornerShape(padding_10), modifier = Modifier.padding(cornerRadius_8)) {
            Column(
                Modifier.padding(horizontalPadding_16),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = text,
                    modifier = Modifier
                        .padding(top = horizontalPadding_16),
                    fontSize = textSize_16sp
                )
                Spacer(
                    modifier = Modifier
                        .padding(padding_10)
                        .fillMaxWidth()
                        .height(2.dp)
                        .background(Color.Gray)
                )

                Text(text = message, modifier = Modifier.padding(vertical = padding_10))

                Row(
                    Modifier
                        .padding(cornerRadius_8)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    DialogButton(
                        backgroundColor = ColorBorderGray,
                        text = stringResource(R.string.bekor)
                    ) {
                        onDismiss()
                    }
                    DialogButton() {
                        onConfirm()
                        onDismiss()
                    }
                }
            }
        }
    }
}