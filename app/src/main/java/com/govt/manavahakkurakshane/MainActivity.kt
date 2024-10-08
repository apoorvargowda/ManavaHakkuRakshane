package com.govt.manavahakkurakshane

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.drawerlayout.widget.DrawerLayout
import com.govt.manavahakkurakshane.common.PreferenceHelper

class MainActivity : AppCompatActivity() {
    lateinit var drawerLayout: DrawerLayout
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<ImageView>(R.id.logout).setOnClickListener {
            PreferenceHelper.defaultPrefs(this).edit().clear().apply()
            finish()
            val i = Intent(this, Login::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(i)
        }

        findViewById<ImageView>(R.id.home).visibility = View.GONE
        // drawer layout instance to toggle the menu icon to open
        // drawer and back button to close drawer
        drawerLayout = findViewById(R.id.drawer_layout)
        actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close)

        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        // to make the Navigation drawer icon always appear on the action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val nhrc_website=findViewById<CardView>(R.id.nhrc)
        nhrc_website.setOnClickListener {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://nhrc.nic.in/")
            startActivity(openURL)
        }

        val kshrc_website=findViewById<CardView>(R.id.kshrc_web)
        kshrc_website.setOnClickListener {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://kshrc.karnataka.gov.in/english")
            startActivity(openURL)
        }

        findViewById<CardView>(R.id.complaint).setOnClickListener {
            startActivity(Intent(this, consent::class.java))
        }

        findViewById<CardView>(R.id.view_complaint).setOnClickListener {
            startActivity(Intent(this, view_complaint::class.java))
        }

        findViewById<CardView>(R.id.order).setOnClickListener {
            startActivity(Intent(this, Order_Complaints::class.java))
        }
        findViewById<CardView>(R.id.rights).setOnClickListener {
            startActivity(Intent(this, our_rights::class.java))
        }
        findViewById<CardView>(R.id.about).setOnClickListener {
            startActivity(Intent(this, about_kshrc::class.java))
        }
        findViewById<CardView>(R.id.Published).setOnClickListener {
            startActivity(Intent(this, Published_Orders::class.java))
        }
        findViewById<CardView>(R.id.contact).setOnClickListener {
            startActivity(Intent(this, contact::class.java))
        }
        findViewById<CardView>(R.id.faqs).setOnClickListener {
            startActivity(Intent(this, Faq::class.java))
        }
    }
    // override the onOptionsItemSelected()
    // function to implement
    // the item click listener callback
    // to open and close the navigation
    // drawer when the icon is clicked
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
    }
}
