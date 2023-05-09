package com.example.mywallett.app.screens.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.mywallett.R
import com.example.mywallett.ui.theme.ColorGray


@Composable
fun SignTextField(
    hint: String,
    testTag: String = "",
    keyboardType: KeyboardType = KeyboardType.Text,
    textStyle: TextStyle = TextStyle(),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChange: (String) -> Unit
) {
    var name by remember { mutableStateOf("") }

    OutlinedTextField(
        value = name,
        onValueChange = { name = it },
        label = { Text(text = hint) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp)
            .testTag(testTag),
        shape = RoundedCornerShape(8.dp),
        textStyle = textStyle,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = Color.Transparent, unfocusedLabelColor = Color.Black,
            backgroundColor = ColorGray
        ),
        keyboardOptions = KeyboardOptions(keyboardType=keyboardType),
        visualTransformation=visualTransformation
    )
}