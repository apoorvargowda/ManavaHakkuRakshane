package com.govt.manavahakkurakshane

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView

class Published_Orders : AppCompatActivity() {
    private lateinit var btnBack: ImageView
    private lateinit var P_order: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_published_orders)

        btnBack = findViewById<ImageView>(R.id.btnback)
        btnBack.setOnClickListener {
            finish()
        }

        P_order = findViewById(R.id.published_order)
        // WebViewClient allows you to handle
        // onPageFinished and override Url loading.
        P_order.webViewClient = WebViewClient()
        // this will load the url of the website
        P_order.loadUrl("https://www.linkedin.com/login")
        // this will enable the javascript settings, it can also allow xss vulnerabilities
        P_order.settings.javaScriptEnabled = true
        // if you want to enable zoom feature
        P_order.settings.setSupportZoom(true)
    }
}