package com.example.mywallett.app.screens.utils

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mywallett.R
import com.example.mywallett.ui.theme.ColorBorderGray

@Composable
fun GoogleBtn(
    color: Color = Color.Black,
    icon: Int = R.drawable.google,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = { onClick() },
        modifier = Modifier
            .size(width = 140.dp, height = 45.dp),
        shape = RoundedCornerShape(cornerRadius_8),
        border = BorderStroke(2.dp, ColorBorderGray)
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = "google",
            tint = color
        )
    }
}