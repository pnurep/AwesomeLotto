package com.dev.gold.awesomelotto.viewmodels

import androidx.lifecycle.MutableLiveData
import com.dev.gold.awesomelotto.utils.UtilsClass
import com.journeyapps.barcodescanner.DecoratedBarcodeView


class QrCodeViewModel : BaseViewModel() {

    private val _flashText = MutableLiveData("Flash On")
    val flashText
        get() = _flashText

    private var flashState = false

    fun hasFlash() = UtilsClass.hasFlash()

    fun toggleFlashlight(v: DecoratedBarcodeView) {
        if (flashState) v.setTorchOff().also { _flashText.value = "Flash On" }
        else v.setTorchOn().also { _flashText.value = "Flash Off" }

        flashState = !flashState
    }
}
