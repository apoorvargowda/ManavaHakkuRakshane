package com.govt.manavahakkurakshane

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class order_complaint_one : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_complaint_one)

        findViewById<ImageView>(R.id.btnback).setOnClickListener {
            finish()
        }
    }
}