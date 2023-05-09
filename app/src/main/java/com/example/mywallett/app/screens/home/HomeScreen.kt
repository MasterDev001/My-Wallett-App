package com.example.mywallett.app.screens.home

import android.annotation.SuppressLint
import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.androidx.AndroidScreen
import com.example.mywallett.R
import kotlinx.coroutines.launch

class HomeScreen : AndroidScreen() {

    @Composable
    override fun Content() {
        HomeScreenContent()
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreenContent() {
    var selectedItem by remember { mutableStateOf(0) }
    val scaffoldState =
        rememberScaffoldState(rememberDrawerState(initialValue = DrawerValue.Closed))
    val scope = rememberCoroutineScope()

    Scaffold(scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(title = {},
                navigationIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.menu),
                        contentDescription = "Menu",
                        modifier = Modifier.clickable {
                            scope.launch { scaffoldState.drawerState.open() }
                        }
                    )
                })
        }, content = {
            if (selectedItem == 0) {
                HomeCScreen()
            } else {
                Settings()
            }
        }, drawerContent = {
            Column(
                Modifier
                    .fillMaxWidth()
                    .size(150.dp)
                    .background(colorResource(id = R.color.black))
            ) {
                Text(text = "asugyhi", fontSize = 36.sp, color = Color.White)
            }

            DropdownMenuItem(onClick = {
                selectedItem = 0
                scope.launch { scaffoldState.drawerState.close() }
            }) {
                Text(text = "Home")
            }
            DropdownMenuItem(onClick = {
                selectedItem = 1
                scope.launch { scaffoldState.drawerState.close() }
            }) {
                Text(text = "Second Screen")
            }
            val activity = (LocalContext.current as Activity)
            BackHandler {
                if (scaffoldState.drawerState.isOpen) {
                    scope.launch { scaffoldState.drawerState.close() }
                } else {
                    activity.finish()
                }
            }
        })
}

@Composable
fun HomeCScreen() {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

    }
}

@Composable
fun Settings() {

}