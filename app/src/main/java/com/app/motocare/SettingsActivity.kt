package com.app.motocare

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Button

class SettingsActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val button_logout = findViewById<Button>(R.id.settings_logout)
        val button_about = findViewById<Button>(R.id.settings_about)

        button_logout.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setMessage("Are you sure?").setPositiveButton("Yes", DialogInterface.OnClickListener() { dialog, which ->
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                dialog.dismiss()
            })
                .setNegativeButton("No",DialogInterface.OnClickListener() { dialog, which ->
                    dialog.dismiss()
                }).show()
        }

        button_about.setOnClickListener {
            val intent = Intent(this, AboutUsActivity::class.java)
            startActivity(intent)
        }
    }
}