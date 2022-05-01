package com.example.mobileapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBar
import com.example.mobileapp.databinding.ActivityProfileBinding
import com.google.firebase.auth.FirebaseAuth

class ProfileActivity : AppCompatActivity() {

    //ViewBinding
    private lateinit var binding:ActivityProfileBinding

    //ActionBar
//    private lateinit var actionBar: ActionBar

    //Firebase authentication
    private lateinit var firebaseAuth: FirebaseAuth

    private var username = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        username = intent.getStringExtra("username").toString()

        //Configure ActonBar
//        actionBar = supportActionBar!!
//        actionBar.title = "Profile"

        //Initializing firebase authentication
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

//        startActivity(Intent(this,UserWelcome::class.java))
//        finish()

        //Handle click, logout
        binding.logoutBtn.setOnClickListener {
            firebaseAuth.signOut()
            checkUser()
        }

        binding.continueButton3.setOnClickListener {
            startActivity(Intent(this, UserWelcome::class.java))
        }
    }


    private fun checkUser() {
        //Check user is logged in or not
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null){
            //User is logged in
            val email = firebaseUser.email
//            val name = firebaseUser.
//            Log.d("d",name.toString())
            //Set to text view
            binding.editTextEmail.text = username
        }
        else{
            //User is not logged in
            startActivity(Intent(this, LoginPage::class.java))
            finish()
        }
    }
}