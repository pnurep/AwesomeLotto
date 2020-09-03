package com.dev.gold.awesomelotto.ui.activity

import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebViewClient
import com.dev.gold.awesomelotto.BR
import com.dev.gold.awesomelotto.R
import com.dev.gold.awesomelotto.databinding.ActivityWebViewBinding
import com.dev.gold.awesomelotto.viewmodels.WebViewViewModel
import javax.inject.Inject


class WebViewActivity(
    override val layoutId: Int = R.layout.activity_web_view
) : BaseActivity<ActivityWebViewBinding>() {

    @Inject
    lateinit var viewModel: WebViewViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.setVariable(BR.viewModel, viewModel)
        binding.executePendingBindings()

        intent.getStringExtra("url")?.let {
            initWebView(it)
        }
    }

    private fun initWebView(url: String) {

        with(binding.webView.settings) {
            mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            javaScriptEnabled = true
            useWideViewPort = true
            loadWithOverviewMode = true
            domStorageEnabled = true
            setSupportMultipleWindows(true)
            cacheMode = WebSettings.LOAD_NO_CACHE

            builtInZoomControls = false
            setSupportZoom(false)
            displayZoomControls = false
        }

        with(binding.webView) {
            webViewClient = WebViewClient()
            webChromeClient = WebChromeClient()

            loadUrl(url)
        }
    }
}
