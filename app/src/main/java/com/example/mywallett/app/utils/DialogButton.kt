package com.example.mywallett.app.utils

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mywallett.R
import com.example.mywallett.ui.theme.ColorGreenButton

@Composable
fun DialogButton(
    text: String = stringResource(R.string.tasdiqlash),
    backgroundColor: Color = ColorGreenButton,
    modifier: Modifier=Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier=modifier,
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            contentColor = Color.White
        )
    ) {
        Text(text = text)
    }

}