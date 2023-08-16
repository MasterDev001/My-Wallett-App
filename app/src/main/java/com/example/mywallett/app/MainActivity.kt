package com.example.mywallett.app

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AlertDialog
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import com.example.mywallett.app.screens.signin.SignInScreen
import com.example.mywallett.navigation.AppNavigatorDispatcher
import com.example.mywallett.ui.theme.ColorBorderGray
import com.example.mywallett.ui.theme.MyWallettTheme
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigatorDispatcher: AppNavigatorDispatcher

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val apiVersion = Build.VERSION.SDK_INT

        val permission = MutableStateFlow(false)
        if (apiVersion < Build.VERSION_CODES.Q) {
            requestPermissions(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) {
                permission.value = it
            }
        }

        setContent {

            val isPermitted by permission.collectAsState()

            if ((apiVersion < Build.VERSION_CODES.Q && isPermitted) || apiVersion >= Build.VERSION_CODES.Q) {

                MyWallettTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                    ) {
                        Navigator(SignInScreen()) { navigator ->
                            LaunchedEffect(key1 = navigator) {
                                navigatorDispatcher.navigation.onEach { it.invoke(navigator) }
                                    .collect()
                            }
                            CurrentScreen()
                        }
                    }
                }
            }else{
                ShowSettings()
            }
        }
    }

    private fun requestPermissions(vararg permissions: String, function: (Boolean) -> Unit) {
        Dexter.withContext(this).withPermissions(*permissions)
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                    report?.let {
                        if (report.areAllPermissionsGranted()) {
                            function(true)
                        } else {
                            showSettingsDialog(*permissions)
                        }
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permisson: MutableList<PermissionRequest>?,
                    token: PermissionToken?
                ) {
                    token?.continuePermissionRequest()
                }
            }).withErrorListener {
                Toast.makeText(this, "Xatolik", Toast.LENGTH_SHORT).show()
            }.check()
    }

    private fun showSettingsDialog(vararg permissions: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Ruxsat kerak !")
        builder.setMessage("${permissions.toString()}larga ruxsat talab etiladi!")
        builder.setPositiveButton("Sozlamalar") { dialog, which ->
            dialog.cancel()
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            val uri = Uri.fromParts("package", packageName, null)
            intent.data = uri
            startActivityForResult(intent, 101)
        }
        builder.setNegativeButton("Bekor") { dialog, which ->
            dialog.cancel()
        }
        builder.show()
    }

    @Composable
    fun ShowSettings() {
        val context = LocalContext.current as MainActivity
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(modifier = Modifier.padding(5.dp)) {
                Text(
                    text = "Ruxsat kerak!",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 3.dp)
                        .height(2.dp)
                        .background(ColorBorderGray)
                )
                Text(
                    text = "Ma'lumotlarni faylga saqlash va ularni o'qish uchun xotiraga ruxsat kerak",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    textAlign = TextAlign.Justify,
                    fontSize = 16.sp,
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    MyButton(
                        text = "Bekor",
                        textSize = 16.sp,
                        onClick = {
                            context.finish()

                        }) {

                    }
                    MyButton(
                        text = "Sozlamalar",
                        textSize = 16.sp,
                        onClick = {
                            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                            val uri = Uri.fromParts("package", context.packageName, null)
                            intent.data = uri
                            context.startActivity(intent)
                        })
                    {
                    }
                }
            }

        }
    }

    @Composable
    fun MyButton(
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        text: String = "Button",
        textSize: TextUnit = 17.sp,
        background: Color = Gray,
        enabled: Boolean = true,
        colors: ButtonColors = ButtonDefaults.buttonColors(

        ),
        border: BorderStroke? = null,
        contentPadding: PaddingValues = ButtonDefaults.ContentPadding,

        interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
        animationDuration: Int = 100,
        scaleUp: Float = 1.1f,
        content: @Composable RowScope.() -> Unit
    ) {

        val coroutineScope = rememberCoroutineScope()

        val scale = remember {
            Animatable(1f)
        }

        Button(
            onClick = {
                coroutineScope.launch(Dispatchers.Main) {
                    scale.animateTo(
                        scaleUp,
                        animationSpec = tween(animationDuration),
                    )
                    scale.animateTo(
                        1f,
                        animationSpec = tween(animationDuration),
                    )
                }
                onClick()
            },
            modifier = modifier
                .scale(
                    scale = scale.value
                ),
//  ,          .clickable(interactionSource = interactionSource, indication = null) {
////
////
////            }
            enabled = enabled,
            colors = colors,
            border = border,
            contentPadding = contentPadding,
            interactionSource = interactionSource,
        ) {
            Text(text = text, fontSize = textSize)
            content()
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