package com.dev.gold.awesomelotto.ui.activity

import android.os.Bundle
import android.view.KeyEvent
import com.dev.gold.awesomelotto.BR
import com.dev.gold.awesomelotto.R
import com.dev.gold.awesomelotto.databinding.ActivityQrCodeBinding
import com.dev.gold.awesomelotto.viewmodels.QrCodeViewModel
import com.journeyapps.barcodescanner.CaptureManager
import javax.inject.Inject


class QrCodeActivity(
    override val layoutId: Int = R.layout.activity_qr_code
) : BaseActivity<ActivityQrCodeBinding>() {

    @Inject
    lateinit var viewModel: QrCodeViewModel

    private val captureManager by lazy { CaptureManager(this, binding.barcodeView) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.setVariable(BR.viewModel, viewModel)
        binding.executePendingBindings()

        captureManager.initCaptureManager(savedInstanceState)
    }

    private fun CaptureManager.initCaptureManager(savedInstanceState: Bundle?) {
        initializeFromIntent(intent, savedInstanceState)
        setShowMissingCameraPermissionDialog(false)
        decode()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?) =
        binding.barcodeView.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event)

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        captureManager.onSaveInstanceState(outState)
    }

    override fun onResume() {
        super.onResume()

        binding.barcodeView.resume()
        captureManager.onResume()
    }

    override fun onPause() {
        super.onPause()

        binding.barcodeView.pause()
        captureManager.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()

        captureManager.onDestroy()
    }
}
