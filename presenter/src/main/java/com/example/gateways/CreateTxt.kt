package com.example.gateways

import android.content.Context
import com.example.a_common.FILE_NAME
import com.example.a_common.FOLDER_NAME
import com.example.a_common.huminizeForFile
import com.example.r_usecase.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.nio.charset.Charset

suspend fun CreateTxt(
    context: Context,
    mapData: LinkedHashMap<String, LinkedHashMap<String, List<String>>>
): Flow<File> = flow {

    val builder = StringBuilder()
    builder.append(
        "${
            System.currentTimeMillis().huminizeForFile()
        }\n${context.getString(R.string.sanagacha_bolgan_malumotlar)}\n\n"
    )
    mapData.onEach { data ->
        builder.append("\n-----  ${data.key}  -----\n")
        data.value.forEach { subItem ->
            builder.append("\n\n${subItem.key}\n")
            subItem.value.forEach {
                builder.append("  ${it}\n")
            }
        }
    }
    val data = builder.toString().toByteArray(Charset.forName("UTF-8"))

    val folder: File? = context.getExternalFilesDir(FOLDER_NAME)
    val file = File(folder, "$FILE_NAME.txt")
    var fileOutputStream: FileOutputStream? = null
    try {
        fileOutputStream = FileOutputStream(file)
        fileOutputStream.write(data)
        emit(file)
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        if (fileOutputStream != null) {
            try {
                fileOutputStream.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

}.flowOn(Dispatchers.IO)