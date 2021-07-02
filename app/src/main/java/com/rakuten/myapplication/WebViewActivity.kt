package com.rakuten.myapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView

class WebViewActivity : AppCompatActivity() {

    companion object {
        private const val WEB_URL = "web_url"

        fun onNewIntent(context: Context, webURL: String): Intent {
            val intent = Intent(context, WebViewActivity::class.java)
            intent.putExtra(WEB_URL, webURL)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        val url = intent.getStringExtra(WEB_URL)
        findViewById<WebView>(R.id.web_view).loadUrl(url!!)
    }
}