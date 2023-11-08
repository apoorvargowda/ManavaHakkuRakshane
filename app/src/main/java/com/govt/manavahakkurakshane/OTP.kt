package com.govt.manavahakkurakshane

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class OTP : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)

        var Number = findViewById<TextView>(R.id.mobile_number)
        var otp =findViewById<EditText>(R.id.pinview)
        var verifyOTPBtn = findViewById<Button>(R.id.btnSubmit)

        val receivedNumber = intent.getStringExtra("mobileNumberKey")
        Number.text = "$receivedNumber"

        verifyOTPBtn.setOnClickListener {
            val input = otp.text.toString()
            if (input == "1234"){
                val intent2 = Intent( this, MainActivity::class.java)
                startActivity(intent2)
            } else {
                Toast.makeText(
                    this@OTP, "Please enter a valid OTP.", Toast.LENGTH_SHORT
                ).show()
            }
        }

    }
}