package com.app.motocare

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val button_register = findViewById<Button>(R.id.button_register)
        val edittext_fullName = findViewById<EditText>(R.id.edittext_fullName)
        val edittext_email = findViewById<EditText>(R.id.edittext_email)
        val edittext_password = findViewById<EditText>(R.id.edittext_password)
        val edittext_confirmPass = findViewById<EditText>(R.id.edittext_confirmPass)
      
        button_register.setOnClickListener {
            val fullName = edittext_fullName.text.toString()
            val email = edittext_email.text.toString()
            val password = edittext_password.text.toString()
            val confirmPass = edittext_confirmPass.text.toString()

            if (TextUtils.isEmpty(fullName) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPass)) {
                Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_LONG).show()
            } else if (password != confirmPass) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            } else {
                UserDataManager.addUser(email,password)
                UserDataManager.saveData(applicationContext)
                val intent = Intent(this, LoginActivity::class.java)               
                startActivity(intent)
            }
        }
    }
}