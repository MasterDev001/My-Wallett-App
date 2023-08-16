package com.example.mywallett.app.screens.share

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.androidx.AndroidScreen
import com.example.a_common.ShareType
import com.example.a_common.huminizeKb
import com.example.mywallett.R
import com.example.mywallett.app.MainActivity
import com.example.mywallett.app.utils.CircularProgress
import com.example.mywallett.ui.theme.ColorGray
import com.example.presenter.share.ShareContract
import com.example.presenter.share.ShareViewModel
import uz.gita.vogayerlib.hiltScreenModel

class ShareScreen : AndroidScreen() {

    @Composable
    override fun Content() {
        val viewModel: ShareViewModel = hiltScreenModel()
        ShareScreenContent(viewModel, viewModel::onEventDispatcher)
    }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    private fun ShareScreenContent(
        viewModel: ShareViewModel,
        onEvent: (ShareContract.Intent) -> Unit
    ) {
        val isSuccess by viewModel.isSuccess.collectAsState()
        val txtFile by viewModel.txtFile.collectAsState()
        val htmlFile by viewModel.htmlFile.collectAsState()
        val pdfFile by viewModel.pdfFile.collectAsState()
        val context = LocalContext.current as MainActivity

        Scaffold(topBar =
        {
            TopAppBar(title = { Text(text = stringResource(R.string.share)) },
                navigationIcon = {
                    IconButton(onClick = {
                        onEvent.invoke(ShareContract.Intent.OpenHome)
                    }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back")
                    }
                })
        }) {
            if (!isSuccess) {
                CircularProgress()
            } else {
                Column(
                    modifier = Modifier
//                    .background(MaterialTheme.customColors.backgroundBrush)
                        .fillMaxSize()
                        .padding(horizontal = 10.dp)
                        .padding(top = 56.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .clickable {
                                ShareFile(
                                    context = context,
                                    shareType = ShareType.TXT,
                                    file = htmlFile
                                )
                            }
                            .padding(horizontal = 12.dp),
                        text = "wallet.html",
                        fontSize = 20.sp, fontWeight = FontWeight.Bold
                    )
                    Text(
                        modifier = Modifier
                            .padding(start = 12.dp),
                        text = "Web brauzerlar uchun (${htmlFile.length().huminizeKb()})",
                        color = ColorGray,
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .clickable {
                                ShareFile(
                                    context = context,
                                    shareType = ShareType.TXT,
                                    file = txtFile
                                )
                            }
                            .padding(horizontal = 12.dp),
                        text = "wallet.txt",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        modifier = Modifier
                            .padding(start = 12.dp),
                        text = "Oddiy text (${txtFile.length().huminizeKb()})",
                        color = ColorGray,
                        fontSize = 12.sp
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .clickable {
                                ShareFile(
                                    context = context,
                                    shareType = ShareType.TXT,
                                    file = pdfFile
                                )
                            }
                            .padding(horizontal = 12.dp),
                        text = "wallet.pdf",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        modifier = Modifier
                            .padding(start = 12.dp),
                        text = "Rasmli fayl (${pdfFile.length().huminizeKb()})",
                        color = ColorGray,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}