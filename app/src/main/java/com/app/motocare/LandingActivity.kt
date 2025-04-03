package com.app.motocare

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button

class LandingActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)

        val nav_stats = findViewById<Button>(R.id.nav_stats)
        val nav_service = findViewById<Button>(R.id.nav_service)
        val nav_profile = findViewById<Button>(R.id.nav_profile)
        val nav_settings = findViewById<Button>(R.id.nav_settings)

        // Navigate to Profile Activity when profile button is clicked
        nav_profile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        nav_settings.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
    }
}