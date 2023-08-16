package com.example.gateways

import android.content.Context
import com.example.a_common.FILE_NAME
import com.example.a_common.FOLDER_NAME
import com.example.a_common.huminizeForFile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.nio.charset.Charset

suspend fun CreateHtml(
    context: Context,
    mapData: LinkedHashMap<String, LinkedHashMap<String, List<String>>>
): Flow<File> = flow {

    val builder = StringBuilder()
    val title = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head> <meta charset=\"utf-8\">" +
            "<style>\n" +
            "table, th, td {\n" +
            "  border: 1px solid black;\n" +
            "  border-collapse: collapse;\n" +
            "  vertical-align: top;\n" +
            "  text-align:start;\n" +
            "}\n" +
            "th, td {\n" +
            "  padding: 5px;\n" +
            "}\n" +
            "</style>\n" +
            "</head>\n" +
            "<body>\n" +
            "<h2 style=\"text-align:start; color: blue;\">${
                System.currentTimeMillis().huminizeForFile()
            } " + context.getString(com.example.r_usecase.R.string.sanagacha_bolgan_malumotlar)

    builder.append(title)
    mapData.onEach { data ->
        val mainTitle = "<table style=\"width:600px ; \">\n" +
                "  <tr  style=\"background-color: #ededed; \">\n" +
                "    <th colspan=\"2\" style=\"text-align:center;\">${data.key}</th>\n" +
                "  </tr>\n"
        builder.append(mainTitle)

        data.value.onEach { subData ->
            builder.append("<tr><td >${subData.key}</td>\n")
            builder.append("<td>")
            subData.value.onEach {
                builder.append("<li>$it</li>")
            }
            builder.append("</td></tr>")
        }
        builder.append("</table></body>")
        builder.append("<br><br>")
    }
    builder.append("</html>")
    val data = builder.toString().toByteArray(Charset.forName("UTF-8"))

    val folder: File? = context.getExternalFilesDir(FOLDER_NAME)
    val file = File(folder, "$FILE_NAME.html")
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