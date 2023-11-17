package com.govt.manavahakkurakshane

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.ThumbnailUtils
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ImageView.GONE
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.govt.manavahakkurakshane.common.PreferenceHelper
import com.manasa.myapplication.shared.K
import java.io.ByteArrayOutputStream
import java.util.Base64
import com.github.gcacace.signaturepad.views.SignaturePad

class Incident : AppCompatActivity() {


    private val documentPickerLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.data?.let { uri ->
                    if (isDocumentSizeValid(uri, 20)) {
                        // Handle the selected document here
                        displaySelectedDocument(uri)
                    } else {
                        Toast.makeText(this, "Selected document exceeds the maximum size of 20 MB", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    private val MAX_DOC_SIZE_MB = 20
    companion object {
        private const val CAMERA_PERMISSION_CODE = 100
        private const val STORAGE_PERMISSION_CODE = 101
    }

    lateinit var I_Date: TextView
    lateinit var submitButton: Button
    lateinit var docImageView: ImageView
    lateinit var docTextView:TextView

    private val CAMERA_REQUEST_CODE_2 = 300
    private var image_1: String = ""

    private val PICK_IMAGE_REQUEST = 1
    private val MAX_IMAGE_SIZE_MB = 5

    private val PICK_VIDEO_REQUEST = 2
    private val MAX_VIDEO_SIZE_MB = 10

    lateinit var signImage: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_incident)


        //---selfie----
        val capturePhoto = findViewById<ImageView>(R.id.selfie)
        capturePhoto.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.CAMERA),
                    CAMERA_REQUEST_CODE_2
                )
            }else {
                val callCameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                // if (callCameraIntent.resolveActivity(packageManager) != null)3
                startActivityForResult(callCameraIntent, CAMERA_REQUEST_CODE_2)
            }
        }

        //---date---
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
        //-----image-replace----
        val imageView: ImageView = findViewById(R.id.photo)
        imageView.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), PICK_IMAGE_REQUEST)
            } else {
                val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST)
            }
        }

        val videoImageView: ImageView = findViewById(R.id.videoimage)
        val video: VideoView = findViewById(R.id.video)
         videoImageView.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), PICK_VIDEO_REQUEST)
            } else {
                val Intent1 = Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(Intent1, PICK_VIDEO_REQUEST)
            }
        }
        video.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), PICK_VIDEO_REQUEST)
            } else {
                val Intent1 = Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(Intent1, PICK_VIDEO_REQUEST)
            }
        }

        docImageView = findViewById(R.id.doc)
        docTextView = findViewById(R.id.docname)
        docImageView.setOnClickListener {
            openDocumentPicker()
        }
        docTextView.setOnClickListener {
            openDocumentPicker()
        }

        signImage = findViewById(R.id.imageView)
    }
    //-----Permissions-----
    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_REQUEST_CODE_2) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val callCameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                // if (callCameraIntent.resolveActivity(packageManager) != null)3
                startActivityForResult(callCameraIntent, CAMERA_REQUEST_CODE_2)
                //Toast.makeText(this, "Camera Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Camera Permission Denied", Toast.LENGTH_SHORT).show()
            }
        } else if (requestCode == PICK_IMAGE_REQUEST) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST)
            //Toast.makeText(this, "Storage Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Storage Permission Denied", Toast.LENGTH_SHORT).show()
            }
        } else if (requestCode == PICK_VIDEO_REQUEST) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(galleryIntent, PICK_VIDEO_REQUEST)
                //Toast.makeText(this, "Storage Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Storage Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //---image capture
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
        when (requestCode) {
            PICK_IMAGE_REQUEST -> handleImageResult(resultCode, data)
            PICK_VIDEO_REQUEST -> handleVideoResult(resultCode, data)
        }
    }
    //---photo replace---
    private fun handleImageResult(resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && data != null) {
            val selectedImageUri = data.data

            if (selectedImageUri != null && isImageSizeValid(selectedImageUri)) {
                val inputStream = contentResolver.openInputStream(selectedImageUri)
                val bitmap = BitmapFactory.decodeStream(inputStream)
                inputStream?.close()

                val imageView: ImageView = findViewById(R.id.photo)
                imageView.setImageBitmap(bitmap)

                Toast.makeText(this, "Image selected successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Selected image exceeds the maximum size of $MAX_IMAGE_SIZE_MB MB", Toast.LENGTH_SHORT).show()
            }
        }
    }
    //---VEDIO replace---
    private fun handleVideoResult(resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && data != null) {
            val selectedVideoUri = data.data

            if (selectedVideoUri != null && isVideoSizeValid(selectedVideoUri)) {
                val imageView: ImageView = findViewById(R.id.videoimage)
                imageView.visibility = View.GONE

                val videoView: VideoView = findViewById(R.id.video)
                videoView.setVideoURI(selectedVideoUri)
                videoView.visibility = VideoView.VISIBLE
                videoView.start()
                Toast.makeText(this, "Video selected successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Selected video exceeds the maximum size of $MAX_VIDEO_SIZE_MB MB", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isImageSizeValid(imageUri: android.net.Uri): Boolean {
        val inputStream = contentResolver.openInputStream(imageUri)
        val imageSizeInBytes = inputStream?.available() ?: 0
        val imageSizeInMB = imageSizeInBytes / (1024 * 1024)

        return imageSizeInMB <= MAX_IMAGE_SIZE_MB
    }

    private fun isVideoSizeValid(videoUri: android.net.Uri): Boolean {
        val fileSize = getFileSize(videoUri)
        val videoSizeInMB = fileSize / (1024 * 1024)

        return videoSizeInMB <= MAX_VIDEO_SIZE_MB
    }

    private fun isDocumentSizeValid(uri: Uri, maxSizeMB: Int): Boolean {
        val fileSize = getFileSize(uri)
        val documentSizeInMB = fileSize / (1024 * 1024)

        return documentSizeInMB <= maxSizeMB
    }
    private fun getFileSize(uri: android.net.Uri): Long {
        val cursor = contentResolver.query(uri, null, null, null, null)
        cursor?.use {
            val sizeIndex = it.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE)
            it.moveToFirst()
            return it.getLong(sizeIndex)
        }
        return 0
    }
    private fun openDocumentPicker() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "*/*" // Any MIME type
        documentPickerLauncher.launch(intent)
    }

    private fun getDocumentName(uri: Uri): String {
        val cursor = contentResolver.query(uri, null, null, null, null)
        cursor?.use {
            val nameIndex = it.getColumnIndexOrThrow(OpenableColumns.DISPLAY_NAME)
            it.moveToFirst()
            return it.getString(nameIndex)
        }
        return ""
    }
    private fun displaySelectedDocument(uri: Uri) {
        docImageView = findViewById(R.id.doc)
        docTextView = findViewById(R.id.docname)

        docImageView.visibility = ImageView.GONE
        docTextView.visibility = TextView.VISIBLE

        val documentName = getDocumentName(uri)
        docTextView.text = "$documentName"
    }

}