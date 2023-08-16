package com.example.presenter.share

import cafe.adriel.voyager.core.model.ScreenModel
import kotlinx.coroutines.flow.StateFlow
import uz.gita.vogayerlib.ScreenModelImpl
import java.io.File


@ScreenModelImpl(ShareViewModelImpl::class)
interface ShareViewModel : ScreenModel {

    val txtFile: StateFlow<File>
    val htmlFile: StateFlow<File>
    val pdfFile: StateFlow<File>
    val isSuccess: StateFlow<Boolean>

    fun onEventDispatcher(intent: ShareContract.Intent)
}