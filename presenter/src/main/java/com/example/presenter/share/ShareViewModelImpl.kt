package com.example.presenter.share

import android.annotation.SuppressLint
import android.app.Application
import cafe.adriel.voyager.core.model.coroutineScope
import com.example.gateways.CreateHtml
import com.example.gateways.CreatePdf
import com.example.gateways.CreateTxt
import com.example.r_usecase.usecases.dataUseCase.DataUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

internal class ShareViewModelImpl @Inject constructor(
    private val direction: ShareDirection,
    private val dataUseCase: DataUseCase,
    private val context: Application,
) : ShareViewModel {

    override val txtFile = MutableStateFlow(File(""))
    override val htmlFile = MutableStateFlow(File(""))
    override val pdfFile = MutableStateFlow(File(""))
    override val isSuccess = MutableStateFlow(false)

    init {
        coroutineScope.launch(Dispatchers.IO) {
            dataUseCase.getAllDataUseC.invoke(context).collect {
                if (it.keys.isNotEmpty()) {
                    val txt = CreateTxt(context, it)
                    val html = CreateHtml(context, it)
                    val pdf = CreatePdf(context, it)
                    combine(txt, html, pdf) { t, h, p ->
                        txtFile.value = t
                        htmlFile.value = h
                        pdfFile.value = p
                        isSuccess.value = true
                    }.collect()
                } else {
                    isSuccess.value = true
                }
            }
        }
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onEventDispatcher(intent: ShareContract.Intent) {
        when (intent) {
            is ShareContract.Intent.OpenHome -> {
                coroutineScope.launch { direction.back() }
            }
        }
    }

}
