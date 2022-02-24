package com.example.mobileapp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.math.sign

class LoginPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_page)

        val signUpBtn = findViewById<Button>(R.id.signUpButton)
        val continueBtn = findViewById<Button>(R.id.continueButton2)
        val username = findViewById<EditText>(R.id.editTextUsername)
        val password = findViewById<EditText>(R.id.editTextPassword)
        val wrongCredentials = findViewById<TextView>(R.id.id_wrong_credentials)


        val tempUsername = "username"
        val tempPassword = "password"

        continueBtn.setOnClickListener {

            if(username.text.toString()==tempUsername && password.text.toString()==tempPassword){
                val homeIntent = Intent(this,WelcomePage::class.java)
                startActivity(homeIntent)
            }else{
                wrongCredentials.text="The Username or Password is incorrect"
                wrongCredentials.setTextColor(Color.RED)

            }

        }

        signUpBtn.setOnClickListener {
            val signUpIntent =Intent(this,SignupPage::class.java)
            startActivity(signUpIntent)
        }

    }
}