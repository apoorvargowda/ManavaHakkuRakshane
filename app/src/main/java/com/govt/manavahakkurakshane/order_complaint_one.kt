package com.govt.manavahakkurakshane

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.govt.manavahakkurakshane.common.PreferenceHelper

class order_complaint_one : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_complaint_one)
        findViewById<TextView>(R.id.heading).text = getString(R.string.order_in_my_complaint)

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

        findViewById<ImageView>(R.id.btnback).setOnClickListener {
            finish()
        }
    }
}