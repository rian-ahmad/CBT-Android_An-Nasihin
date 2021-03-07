package com.cbt.anasihin

import android.annotation.SuppressLint
import android.net.http.SslError
import android.os.Bundle
import android.webkit.SslErrorHandler
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity


class WebActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cbt)

        val webView: WebView = findViewById(R.id.webView)
        val cbtUrl = "http://10.0.2.2/anasihin/"
        initWebView()
        setWebClient()
        handlePullToRefresh()
        webView.loadUrl(cbtUrl)
    }

    private fun handlePullToRefresh() {

    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView() {
        val webView: WebView = findViewById(R.id.webView)
        webView.settings.javaScriptEnabled = true
        webView.settings.loadWithOverviewMode = true
        webView.settings.useWideViewPort = true
        webView.settings.domStorageEnabled = true
        webView.settings.setSupportZoom(true)
        webView.settings.builtInZoomControls = true
        webView.settings.displayZoomControls = false

        webView.webViewClient = object : WebViewClient() {
            override
            fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
                handler?.proceed()
            }
        }

    }

    private fun setWebClient() {
        val webView: WebView = findViewById(R.id.webView)
        val progressBar: ProgressBar = findViewById(R.id.progressBar)
        val maxProgress = 100
        webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(
                    view: WebView,
                    newProgress: Int
            ) {
                super.onProgressChanged(view, newProgress)
                progressBar.progress = newProgress
                if (newProgress < maxProgress && progressBar.visibility == ProgressBar.GONE) {
                    progressBar.visibility = ProgressBar.VISIBLE
                }

                if (newProgress == maxProgress) {
                    progressBar.visibility = ProgressBar.GONE
                }
            }
        }
    }
}