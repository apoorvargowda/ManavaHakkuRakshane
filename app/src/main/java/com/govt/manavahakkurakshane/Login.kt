package com.govt.manavahakkurakshane


import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.govt.manavahakkurakshane.common.PreferenceHelper
import java.util.Locale
import java.util.regex.Matcher
import java.util.regex.Pattern

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var edtPhone = findViewById<EditText>(R.id.etd_mobile)
        val generateOTPBtn = findViewById<Button>(R.id.btnLogin)
        generateOTPBtn.setOnClickListener {
            val mobileNumber = edtPhone.text.toString()
            if (!isValid(mobileNumber)) {
                Toast.makeText(
                    this@Login, "Please enter a valid phone number.", Toast.LENGTH_SHORT
                ).show()
            } else {
                val intent = Intent( this, OTP::class.java)
                intent.putExtra("mobileNumberKey",mobileNumber)
                startActivity(intent)
            }
        }


        val prefs = PreferenceHelper.defaultPrefs(this)
        val languageCode = prefs.getString("LAN","")
        if(languageCode.isNullOrEmpty()) {
            setAppLanguage("kn")
        }
        val engBtn = findViewById<Button>(R.id.btn_eng)
        val knBtn = findViewById<Button>(R.id.btn_kan)
        val hiBtn = findViewById<Button>(R.id.btn_hin)
        when(languageCode){
            "en" -> {
                engBtn.setBackgroundDrawable(resources.getDrawable(R.drawable.btn_lang_round))
            }
            "kn" -> {
                knBtn.setBackgroundDrawable(resources.getDrawable(R.drawable.btn_lang_round))
            }
            "hi" -> {
                hiBtn.setBackgroundDrawable(resources.getDrawable(R.drawable.btn_lang_round))
            }
        }

        findViewById<Button>(R.id.btn_eng).setOnClickListener {
            setAppLanguage("en")
        }
        findViewById<Button>(R.id.btn_kan).setOnClickListener {
            setAppLanguage("kn")
        }
        findViewById<Button>(R.id.btn_hin).setOnClickListener {
            setAppLanguage("hi")
        }
    }

    private fun setAppLanguage(languageCode: String) {
        val prefs = PreferenceHelper.defaultPrefs(this)
        prefs.edit().putString("LAN",languageCode).commit()
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val resources = resources
        val config: Configuration = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
        ActivityCompat.recreate(this)
    }
    fun isValid(s: String?): Boolean {

        // The given argument to compile() method is regular expression. With the help of
        // regular expression we can validate mobile number.
        // 1) Begins with 0 or 91
        // 2) Then contains 6,7 or 8 or 9.
        // 3) Then contains 9 digits
        val p: Pattern = Pattern.compile("^[6-9][0-9]{9}")

        // Pattern class contains matcher() method to find matching between given number and regular expression
        val m: Matcher = p.matcher(s)
        return m.find() && m.group().equals(s)
    }

}