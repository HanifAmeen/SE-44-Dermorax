package com.example.mobileapp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.*

class SignupPage : AppCompatActivity() {
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_page)

        // Reference for the user node
        databaseReference = FirebaseDatabase.getInstance().getReference("User")

        val signInBtn = findViewById<Button>(R.id.signInButton)
        val username = findViewById<EditText>(R.id.editTextUsername)
        val password = findViewById<EditText>(R.id.editTextPassword)
        val email = findViewById<EditText>(R.id.editTextEmail)
        val wrongCredentials = findViewById<TextView>(R.id.id_wrong_credentials2)
        val continueBtn = findViewById<Button>(R.id.continueButton)

        //Sign in Button
        signInBtn.setOnClickListener {
            val signInIntent = Intent(this,LoginPage::class.java)
            startActivity(signInIntent)
        }
        //Continue Button
        continueBtn.setOnClickListener {

            //Checking for existing usernames
            val details = checkExistingUserName(username.text.toString())
            Log.d("My", details.toString())
            if(!details){
                val user = User (username.text.toString(),password.text.toString(),email.text.toString())

                databaseReference.child(username.text.toString()).setValue(user).addOnSuccessListener {
                    Toast.makeText(this,"Successfully Saved", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener{
                    Toast.makeText(this,"Failed", Toast.LENGTH_SHORT).show()
                }

                val getStartedIntent = Intent(this,UserWelcome::class.java)
                startActivity(getStartedIntent)

                username.setText("")
                password.setText("")
                email.setText("")

            }else{
                wrongCredentials.text="An account already exist for this username"
                wrongCredentials.setTextColor(Color.RED)
            }

        }
    }
    private fun checkExistingUserName(username: String):Boolean{
        var result = false
        databaseReference = FirebaseDatabase.getInstance().getReference("User")
        databaseReference.child(username).get().addOnSuccessListener {

            if(it.exists()){
                result=true
            }
        }
        return result
    }
}