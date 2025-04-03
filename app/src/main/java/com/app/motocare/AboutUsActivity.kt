package com.app.motocare

import android.app.Activity
import android.os.Bundle
import android.widget.Button

class AboutUsActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)

        // Find the back button from the layout
        val buttonBack = findViewById<Button>(R.id.buttonBack)

        // Set click listener to finish the activity when back button is clicked
        buttonBack.setOnClickListener {
            finish()
        }
    }
}