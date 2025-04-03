package com.app.motocare

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : Activity() {

    private lateinit var editPassword: EditText
    private lateinit var editMobile: EditText
    private lateinit var editTel: EditText
    private lateinit var editAddress: EditText
    private lateinit var editPostalCode: EditText
    private lateinit var buttonEditProfile: Button
    private lateinit var buttonSaveChanges: Button
    private lateinit var buttonBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        initViews()
        setupListeners()
    }

    private fun initViews() {
        editPassword = findViewById(R.id.editPassword)
        editMobile = findViewById(R.id.editMobile)
        editTel = findViewById(R.id.editTel)
        editAddress = findViewById(R.id.editAddress)
        editPostalCode = findViewById(R.id.editPostalCode)
        buttonEditProfile = findViewById(R.id.buttonEditProfile)
        buttonSaveChanges = findViewById(R.id.buttonSaveChanges)
        buttonBack = findViewById(R.id.buttonBack)
    }

    private fun setupListeners() {
        buttonEditProfile.setOnClickListener {
            enableEditing(true)
        }

        buttonSaveChanges.setOnClickListener {
            saveProfile()
            enableEditing(false)
        }

        buttonBack.setOnClickListener {
            finish()
        }
    }

    private fun enableEditing(enable: Boolean) {
        editPassword.isEnabled = enable
        editMobile.isEnabled = enable
        editTel.isEnabled = enable
        editAddress.isEnabled = enable
        editPostalCode.isEnabled = enable

        buttonEditProfile.visibility = if (enable) View.GONE else View.VISIBLE
        buttonSaveChanges.visibility = if (enable) View.VISIBLE else View.GONE
    }

    private fun saveProfile() {
        Toast.makeText(this, "Profile Updated Successfully!", Toast.LENGTH_SHORT).show()
    }
}