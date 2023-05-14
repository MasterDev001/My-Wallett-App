package com.example.mywallett.app.screens.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.*
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.mywallett.R
import com.example.mywallett.ui.theme.ColorGray


@Composable
fun PasswordTextField(
    text:String,
    hint: String="",
    testTag: String = "",
    textStyle: TextStyle = TextStyle(),
    isError:Boolean=false,
    onValueChange: (String) -> Unit
) {
    var passwordVisibility by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = text,
        onValueChange = onValueChange,
        label = { Text(text = hint) },
        maxLines = 1,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp)
            .testTag(testTag),
        shape = RoundedCornerShape(8.dp),
        textStyle = textStyle,
        isError=isError,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = Color.Transparent, unfocusedLabelColor = Color.Black,
            backgroundColor = ColorGray
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = if (passwordVisibility) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        placeholder = { Text(text = stringResource(R.string.password)) },
        trailingIcon = {
            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                if(!passwordVisibility){
                    Text(
                        text = "Show",
                        color = Color.Black,
                        modifier = Modifier.padding(end = horizontalPadding_16)
                    )
                }else{
                    Text(
                        text = "Hide",
                        color = Color.Black,
                        modifier = Modifier.padding(end = horizontalPadding_16)
                    )
                }
            }
        }
    )
}