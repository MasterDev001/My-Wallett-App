package com.example.mywallett.app.screens.signin

import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import com.example.mywallett.R
import com.example.mywallett.app.screens.utils.*
import com.example.mywallett.ui.theme.ColorGreenButton


class SignInScreen : AndroidScreen() {

    @Composable
    override fun Content() {

        SignInContent()
    }
}

@Composable
fun SignInContent() {
    Column(
        Modifier
            .padding(30.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        val checkState = remember { mutableStateOf(true) }

        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                modifier = Modifier.padding(bottom = 36.dp),
                text = stringResource(R.string.signIN),
                style = MaterialTheme.typography.h3
            )
            SignTextField(
                hint = stringResource(R.string.eMail),
                onValueChange = {},
                keyboardType = KeyboardType.Email
            )
            PasswordTextField(hint = stringResource(id = R.string.password), onValueChange = {})

            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Row {
                    Switch(
                        checked = checkState.value,
                        onCheckedChange = { checkState.value = it },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.White,
                            checkedTrackColor = Color.Green,
                            uncheckedThumbColor = Color.White,
                            uncheckedTrackColor = Color.Black
                        )
                    )
                    Text(
                        text = stringResource(R.string.remember),
                        modifier = Modifier
                            .padding(10.dp)
                            .focusable(true)
                    )
                }

                Text(text = stringResource(R.string.forgotPassword),
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .clickable {

                        }
                        .focusable(true))
            }
        }
        PrimaryButton(text = stringResource(R.string.signIN), verticalPadding = 50.dp) {

        }
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Divider(color = Color.Black, modifier = Modifier.width(85.dp))
            Text(
                text = stringResource(R.string.orSIgnIn), modifier = Modifier.padding(5.dp)
            )
            Divider(color = Color.Black, modifier = Modifier.width(85.dp))
        }
        Row(
            Modifier
                .padding(top = horizontalPadding_20)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            GoogleBtn {

            }
            GoogleBtn(icon = R.drawable.apple) {

            }
        }
    }
    Row(
        Modifier
            .padding(bottom = bottomPadding_25)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Bottom
    ) {
        Text(text = stringResource(R.string.donthaveanaccount))
        Text(
            text = stringResource(R.string.register),
            color = ColorGreenButton,
            modifier = Modifier.clickable {

            }
        )
    }
}