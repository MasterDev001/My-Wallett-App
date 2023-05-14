package com.example.mywallett.app.screens.register

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import com.example.mywallett.R
import com.example.mywallett.app.screens.utils.*
import com.example.mywallett.ui.theme.ColorGreenButton
import com.example.presenter.signUp.SignUpContract
import com.example.presenter.signUp.SignUpViewModel
import uz.gita.vogayerlib.hiltScreenModel


class RegisterScreen : AndroidScreen() {

    @Composable
    override fun Content() {
        val viewModel: SignUpViewModel = hiltScreenModel()
        val uiState = viewModel.uiStateFlow.collectAsState()
        RegisterScreenContent(uiState, viewModel::onEventDispatcher)
    }
}

@Composable
fun RegisterScreenContent(
    uiState: State<SignUpContract.UiState>, onEventDispatcher: (SignUpContract.Intent) -> Unit
) {
    val name = remember { mutableStateOf("") }
    val password = rememberSaveable { mutableStateOf("") }
    val verifyPassword = rememberSaveable { mutableStateOf("") }
    val email = rememberSaveable { mutableStateOf("") }
    val checkState = remember { mutableStateOf(true) }
    val emailError = remember { mutableStateOf(false) }
    val passwordError = remember { mutableStateOf(false) }
    val nameError = remember { mutableStateOf(false) }

    Column(
        Modifier
            .padding(30.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                modifier = Modifier.padding(bottom = 36.dp),
                text = stringResource(R.string.register),
                style = MaterialTheme.typography.h3
            )
            SignTextField(
                text = name.value,
                hint = stringResource(R.string.nameSurname),
                onValueChange = {
                    name.value = it
                    if (nameError.value && name.value != "") nameError.value = false
                },
                keyboardType = KeyboardType.Email,
                isError = nameError.value
            )

            SignTextField(
                text = email.value,
                hint = stringResource(R.string.eMail),
                onValueChange = {
                    email.value = it
                    if (isValidEmail(email.value)) emailError.value = false
                },
                keyboardType = KeyboardType.Email,
                isError = emailError.value
            )

            PasswordTextField(
                text = password.value,
                hint = stringResource(id = R.string.password),
                onValueChange = {
                    password.value = it
                    if (password.value.length > 7 && password.value == verifyPassword.value) {
                        passwordError.value = false
                    }
                },
                isError = passwordError.value
            )
            PasswordTextField(
                text = verifyPassword.value,
                hint = stringResource(R.string.confirmPassword),
                onValueChange = {
                    verifyPassword.value = it
                    if (verifyPassword.value.length > 7 && password.value == verifyPassword.value) {
                        passwordError.value = false
                    }
                },
                isError = passwordError.value
            )

        }

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
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
                    .padding(top = 10.dp)
                    .focusable(true)
            )
        }

        PrimaryButton(text = stringResource(R.string.register), verticalPadding = 20.dp) {
            if (name.value == "") {
                nameError.value = true
            }
            when {
                !isValidEmail(email.value) -> emailError.value = true
                password.value.length < 8 || password.value != verifyPassword.value -> passwordError.value =
                    true

                else -> {
                    onEventDispatcher.invoke(
                        SignUpContract.Intent.Register(
                            name.value, email.value, password.value
                        )
                    )
                }
            }
        }
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Divider(color = Color.Black, modifier = Modifier.width(85.dp))
            Text(
                text = stringResource(R.string.orSIgnIn),
                modifier = Modifier.padding(5.dp)
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
        Text(text = stringResource(R.string.doYouHaveAnAcc))
        Text(text = stringResource(R.string.signIN),
            color = ColorGreenButton,
            modifier = Modifier.clickable {
           onEventDispatcher.invoke( SignUpContract.Intent.OpenSignIn)
            })
    }

    if (uiState.value.isLoading == true) {
        CircularProgress()
    } else if (uiState.value.message != null) {
//            scope.launch {
//                scaffoldState.snackbarHostState.showSnackbar(uiState.value.message.toString())
//            }
        Log.d("TAG1111", "SignInContent: ${uiState.value.message.toString()}")
    } else if (uiState.value.error != null) {
//            scope.launch {
//                scaffoldState.snackbarHostState.showSnackbar(uiState.value.error.toString())
//            }
        Log.d("TAG1111", "SignInContent: ${uiState.value.error.toString()}")

    }
}