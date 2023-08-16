package com.example.mywallett.app.screens.share

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.example.a_common.ShareType
import com.example.mywallett.BuildConfig
import com.example.mywallett.R
import java.io.File


fun ShareFile(context:Context,shareType: ShareType, file: File) {

    val uri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".provider", file)
    var type = ""
    when (shareType) {
        ShareType.PDF -> {
            type = "application/pdf"
        }

        ShareType.HTML -> {
            type = "text/html"
        }

        ShareType.TXT -> {
            type ="text/plain"
        }
    }

    val intent = Intent(Intent.ACTION_SEND)
    intent.type = type
    intent.putExtra(Intent.EXTRA_STREAM, uri)
    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

    ContextCompat.startActivity(
        context,
        Intent.createChooser(intent, context.getString(R.string.sharewith)),
        null
    )
}