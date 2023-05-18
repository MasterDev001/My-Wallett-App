package com.example.mywallett.app.screens.signin

import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import com.example.mywallett.R
import com.example.mywallett.app.screens.utils.*
import com.example.mywallett.ui.theme.ColorGreenButton
import com.example.presenter.signin.LoginContract
import com.example.presenter.signin.SignInViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.launch
import uz.gita.vogayerlib.hiltScreenModel


class SignInScreen : AndroidScreen() {

    @Composable
    override fun Content() {
        val viewModel: SignInViewModel = hiltScreenModel()
        val uiState = viewModel.uiStateFlow.collectAsState()
        SignInContent(uiState, viewModel::onEventDispatcher)
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun SignInContent(
    uiState: State<LoginContract.UiState>,
    onEvent: (LoginContract.Intent) -> Unit
) {
    val password = rememberSaveable { mutableStateOf("") }
    val email = rememberSaveable { mutableStateOf("") }
    val checkState = remember { mutableStateOf(true) }
    val passwordError = remember { mutableStateOf(false) }
    val emailError = remember { mutableStateOf(false) }
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {
            val account = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                val result = account.getResult(ApiException::class.java)
                val credentials = GoogleAuthProvider.getCredential(result.idToken, null)
                onEvent.invoke(LoginContract.Intent.SignWithGoogle(credentials))
            } catch (e: ApiException) {
                Log.d("TAG1111", "$e")
            }
        }

    Scaffold(Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        snackbarHost = {
            SnackbarHost(it) { snackbar ->
                Snackbar(
                    backgroundColor = Color.Black,
                    actionColor = Color.Red,
                    contentColor = Color.Red,
                    snackbarData = snackbar
                )
            }
        }
    ) {
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
                    text = stringResource(R.string.signIN),
                    style = MaterialTheme.typography.h3
                )
                SignTextField(
                    text = email.value,
                    hint = stringResource(R.string.eMail),
                    isError = emailError.value,
                    onValueChange = {
                        email.value = it
                        if (isValidEmail(email.value)) emailError.value = false
                    },
                    keyboardType = KeyboardType.Email
                )
                PasswordTextField(
                    text = password.value,
                    hint = stringResource(id = R.string.password),
                    isError = passwordError.value,
                    onValueChange = {
                        password.value = it
                        if (password.value.length > 7) passwordError.value = false
                    })

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
                when {
                    !isValidEmail(email.value) -> emailError.value = true
                    password.value.length < 8 -> passwordError.value = true
                    else -> {
                        Log.d("TAG12", "SinIng:1")
                        onEvent.invoke(
                            LoginContract.Intent.Login(
                                email.value,
                                password.value,
                                checkState.value
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
                    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken("985140081211-9bv972kuq39ocfhqpu0t91j9bcda2un6.apps.googleusercontent.com")
                        .requestEmail()

                        .build()

                    val googleSignInClient = GoogleSignIn.getClient(context, gso)

                    launcher.launch(googleSignInClient.signInIntent)
                }
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
                onEvent.invoke(LoginContract.Intent.OpenRegister)
            }
        )
    }


    when (uiState.value) {
        is LoginContract.UiState.Loading -> {
            CircularProgress()
        }

        is LoginContract.UiState.Error -> {
            Log.d("TAG12", "SinIng: ${(uiState.value as LoginContract.UiState.Error).message}")
        }
    }
//    when (uiState.value.isLoading == true) {
//        CircularProgress()
//    } else {
//        scope.launch {
//            scaffoldState.snackbarHostState.showSnackbar(uiState.value.message.toString())
//        }
//        Log.d("TAG1111", "SignInContent: ${uiState.value.message.toString()}")
//    } else if (uiState.value.error != null) {
//        Log.d("TAG1111", "SignInContent: ${uiState.value.error.toString()}")
//        scope.launch {
//            scaffoldState.snackbarHostState.showSnackbar(uiState.value.error.toString())
//        }

}

