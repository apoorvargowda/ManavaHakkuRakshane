package com.govt.manavahakkurakshane

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import com.govt.manavahakkurakshane.common.PreferenceHelper

class our_rights : AppCompatActivity() {
    private lateinit var ourrights: WebView
    private lateinit var btnBack: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_our_rights)

        findViewById<ImageView>(R.id.logout).setOnClickListener {
            PreferenceHelper.defaultPrefs(this).edit().clear().apply()
            finish()
            val i = Intent(this, Login::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(i)
        }
        findViewById<ImageView>(R.id.home).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        btnBack = findViewById<ImageView>(R.id.btnback)
        btnBack.setOnClickListener {
            finish()
        }

        ourrights = findViewById(R.id.ourright)
        // WebViewClient allows you to handle
        // onPageFinished and override Url loading.
        ourrights.webViewClient = WebViewClient()
        // this will load the url of the website
        ourrights.loadUrl("https://www.geeksforgeeks.org/")
        // this will enable the javascript settings, it can also allow xss vulnerabilities
        ourrights.settings.javaScriptEnabled = true
        // if you want to enable zoom feature
        ourrights.settings.setSupportZoom(true)
    }


}