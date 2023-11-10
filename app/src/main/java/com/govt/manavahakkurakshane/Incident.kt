package com.govt.manavahakkurakshane

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import com.govt.manavahakkurakshane.common.PreferenceHelper
import com.manasa.myapplication.shared.K
import java.io.ByteArrayOutputStream
import java.util.Base64

class Incident : AppCompatActivity() {

    lateinit var I_Date: TextView
    lateinit var submitButton: Button
    private val CAMERA_REQUEST_CODE_2 = 300
    private var image_1: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_incident)

        val capturePhoto = findViewById<ImageView>(R.id.selfie)
        capturePhoto.setOnClickListener {
            val callCameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            // if (callCameraIntent.resolveActivity(packageManager) != null)3
            startActivityForResult(callCameraIntent, CAMERA_REQUEST_CODE_2)
        }

        I_Date = findViewById<TextView>(R.id.i_date)
        I_Date.setOnClickListener {
            K.show_calender(this, I_Date)
        }

        findViewById<Button>(R.id.btncancel).setOnClickListener {
            PreferenceHelper.defaultPrefs(this).edit().clear().apply()
            finish()
            val i = Intent(this, MainActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(i)
        }
        //-----pop-up
        submitButton = findViewById<Button>(R.id.btnsubmit)
        submitButton.setOnClickListener {
            // Show the pop-up modal
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Form Submitted")
            builder.setMessage("Your Complaint has been successfully submitted.")
            builder.setPositiveButton("OK") { dialog, which ->
                // Redirect to the dashboard
                PreferenceHelper.defaultPrefs(this).edit().clear().apply()
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
            builder.show()
        }


    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            CAMERA_REQUEST_CODE_2 -> {
                if (resultCode == Activity.RESULT_OK && data != null) {
                    var bmp = data.extras?.get("data") as Bitmap
                    val img1 = findViewById<ImageView>(R.id.selfie)
                    img1.setImageBitmap(bmp)

                    // convert bmp to base64
                    val byteArrayOutputStream = ByteArrayOutputStream()
                    bmp.compress(Bitmap.CompressFormat.JPEG, 30, byteArrayOutputStream)
                    val byteArray: ByteArray = byteArrayOutputStream.toByteArray()

                    this.image_1 = Base64.getEncoder().encodeToString(byteArray)
                }
            }
        }
    }


}