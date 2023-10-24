package com.example.recipesearch.ui.screens.web

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Composable
fun WebViewScreen(url: String){
    AndroidView(factory = {
        WebView(it).apply {
            webViewClient = WebViewClient()
            loadUrl(url)
        }
    })
}