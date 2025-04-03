package com.app.motocare

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val button_login = findViewById<Button>(R.id.button_login)
        val button_register = findViewById<Button>(R.id.button_register)
        val edittext_username = findViewById<EditText>(R.id.edittext_username)
        val edittext_password = findViewById<EditText>(R.id.edittext_password)

        //Login Button
        button_login.setOnClickListener {
            val username = edittext_username.text.toString()
            val password = edittext_password.text.toString()

            if(TextUtils.isEmpty(username)&&TextUtils.isEmpty(password)){
                Toast.makeText(this,"Please enter username and password.",Toast.LENGTH_LONG).show()
            }else if(TextUtils.isEmpty(username)){
                Toast.makeText(this,"Please enter username.",Toast.LENGTH_LONG).show()
            }else if(TextUtils.isEmpty(password)){
                Toast.makeText(this,"Please enter password.",Toast.LENGTH_LONG).show()
            }else{
                val intent = Intent(this, LandingActivity::class.java)
                startActivity(intent)
            }
        }

        //Register Button
        button_register.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}