package com.example.mobileapp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class SignupPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_page)

        val signInBtn = findViewById<Button>(R.id.signInButton)
        val username = findViewById<EditText>(R.id.editTextUsername)
        val password = findViewById<EditText>(R.id.editTextPassword)
        val email = findViewById<EditText>(R.id.editTextEmail)
        val wrongCredentials = findViewById<TextView>(R.id.id_wrong_credentials2)
        val continueBtn = findViewById<Button>(R.id.continueButton)

        var tempEmail = "dermorax@gmail.com"

        signInBtn.setOnClickListener {
            val signInIntent = Intent(this,LoginPage::class.java)
            startActivity(signInIntent)
        }

        continueBtn.setOnClickListener {
            if(email.text.toString()!=tempEmail){
                val getStartedIntent = Intent(this,UserWelcome::class.java)
                startActivity(getStartedIntent)
                username.text
                password.text
            }else{
                wrongCredentials.text="An account exist for this email"
                wrongCredentials.setTextColor(Color.RED)
            }

        }
    }
}