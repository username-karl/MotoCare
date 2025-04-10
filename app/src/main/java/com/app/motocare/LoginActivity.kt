package com.app.motocare

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class LoginActivity : Activity() {
    data class Motorcycle(val makeModel: String, val mileage: String)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        UserDataManager.loadData(applicationContext)

        val button_login = findViewById<Button>(R.id.button_login)

        val button_register = findViewById<Button>(R.id.button_register)
        val edittext_email = findViewById<EditText>(R.id.edittext_email)
        val edittext_password = findViewById<EditText>(R.id.edittext_password)


        // Initialize with a default user if no users are present
        if (UserDataManager.getAllUsers().isEmpty()){UserDataManager.addUser("test@example.com", "password")}

        //Login Button
        button_login.setOnClickListener {
            val email = edittext_email.text.toString()
            val password = edittext_password.text.toString()

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_LONG).show()
            } else {
                val user = UserDataManager.getUser(email)
                if (user != null && user.first == password) {
                    Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, LandingActivity::class.java)
                    intent.putExtra("user_email", email)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Invalid email or password", Toast.LENGTH_LONG).show()
                }
                UserDataManager.saveData(applicationContext)
            }
        }

        //Register Button
        button_register.setOnClickListener {
            val email = edittext_email.text.toString()
            val password = edittext_password.text.toString()
            val user = UserDataManager.getUser(email)
            if (user == null) {
                if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
                    UserDataManager.addUser(email, password)
                    UserDataManager.saveData(applicationContext)
                    Toast.makeText(this, "Registration Successful. Please Log In", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, LoginActivity::class.java))
                }
            } else {
                Toast.makeText(this, "Email already used.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}