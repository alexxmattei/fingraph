package com.example.fingraph.home.ui.news

import android.os.Bundle
import android.webkit.WebView
import com.example.fingraph.R
import com.example.fingraph.base.BaseActivity

class NewsActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        actionBar?.hide()
        supportActionBar?.hide()

        val myWebView: WebView = findViewById(R.id.news_web_view)
        myWebView.settings.javaScriptEnabled = true

        var newsData = "https://cointelegraph.com/"

        if (intent.extras != null) {
            newsData = intent.getSerializableExtra("METADATA_URL") as String
        }
        myWebView.loadUrl(newsData)
    }
}