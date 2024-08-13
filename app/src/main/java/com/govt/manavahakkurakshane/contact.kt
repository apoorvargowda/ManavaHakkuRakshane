package com.govt.manavahakkurakshane

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.TextView
import com.govt.manavahakkurakshane.common.PreferenceHelper

class contact : AppCompatActivity() {
    private lateinit var btnBack: ImageView
    private lateinit var contact_detail: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        btnBack = findViewById<ImageView>(R.id.btnback)
        btnBack.setOnClickListener {
            finish()
        }
        findViewById<TextView>(R.id.heading).text = getString(R.string.contact)

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

        contact_detail = findViewById(R.id.d_contact)
        // WebViewClient allows you to handle
        // onPageFinished and override Url loading.
        contact_detail.webViewClient = WebViewClient()
        // this will load the url of the website
        contact_detail.loadUrl("")
        // this will enable the javascript settings, it can also allow xss vulnerabilities
        contact_detail.settings.javaScriptEnabled = true
        // if you want to enable zoom feature
        contact_detail.settings.setSupportZoom(true)
    }
}