package com.govt.manavahakkurakshane

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.govt.manavahakkurakshane.common.PreferenceHelper

class consent : AppCompatActivity() {
    private lateinit var webView: WebView
    private lateinit var consentCheckBox: CheckBox
    private lateinit var submitButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consent)

        findViewById<TextView>(R.id.heading).text = "Consent"
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

        consentCheckBox = findViewById(R.id.consentCheckBox)
        submitButton = findViewById(R.id.btnsubmit)

        consentCheckBox.setOnCheckedChangeListener { _, isChecked ->
            submitButton.isEnabled = isChecked
        }

        submitButton.setOnClickListener {
            if (consentCheckBox.isChecked) {
                // Execute your submission logic here
                startActivity(Intent(this, complaint_detail::class.java))
            } else {
                showToast("Please check the consent")
            }
        }

        //-----------WebView--------------
        webView = findViewById(R.id.webView)
        // WebViewClient allows you to handle
        // onPageFinished and override Url loading.
        webView.webViewClient = WebViewClient()
        // this will load the url of the website
        webView.loadUrl("")
        // this will enable the javascript settings, it can also allow xss vulnerabilities
        webView.settings.javaScriptEnabled = true
        // if you want to enable zoom feature
        webView.settings.setSupportZoom(true)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


}