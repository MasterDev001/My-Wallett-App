package com.example.mywallett.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import com.example.mywallett.app.screens.signin.SignInScreen
import com.example.mywallett.navigation.AppNavigatorDispatcher
import com.example.mywallett.ui.theme.MyWallettTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
     lateinit var navigatorDispatcher : AppNavigatorDispatcher

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyWallettTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    Navigator(SignInScreen()) { navigator ->
                        LaunchedEffect(key1 = navigator) {
                            navigatorDispatcher.navigation.onEach { it.invoke(navigator) }.collect()
                        }
                        CurrentScreen()
                    }
                }
            }
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    MyWallettTheme {
//       SignInContent()
//    }
//}