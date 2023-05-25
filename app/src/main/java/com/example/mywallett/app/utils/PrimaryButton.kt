package com.example.mywallett.app.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.mywallett.ui.theme.ColorGreenButton

@Composable
fun PrimaryButton(
    text: String,
    color: Color = ColorGreenButton,
    verticalPadding: Dp =0.dp,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        modifier = Modifier
            .padding(horizontal = horizontalPadding_20, vertical = verticalPadding)
            .fillMaxWidth()
            .height(primaryButtonHeight),
        shape = RoundedCornerShape(primaryCornerRadius_12),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = ColorGreenButton,
            contentColor = Color.Black
        )
    ) {
        Text(text = text, fontSize = buttonTextSize_27sp)
    }
}