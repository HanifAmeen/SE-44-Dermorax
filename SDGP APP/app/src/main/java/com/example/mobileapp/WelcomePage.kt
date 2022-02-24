package com.example.mobileapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class WelcomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.welcome_page)

        val signIN = findViewById<Button>(R.id.buttonSignIN)
        val signUP = findViewById<Button>(R.id.buttonSignUP)

        signIN.setOnClickListener {
            val signInIntent = Intent(this,LoginPage::class.java)
            startActivity(signInIntent)
        }

        signUP.setOnClickListener {
            val signUpIntent = Intent(this,SignupPage::class.java)
            startActivity(signUpIntent)
        }

    }
}