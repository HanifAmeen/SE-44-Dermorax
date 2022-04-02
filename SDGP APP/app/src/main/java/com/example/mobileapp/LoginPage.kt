package com.example.mobileapp

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.mobileapp.databinding.LoginPageBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class LoginPage : AppCompatActivity() {

    //ViewBinding
    private lateinit var binding: LoginPageBinding

    //ActionBar
//    private lateinit var actionBar: ActionBar

    //ProgressDialog
   private lateinit var progressDialog:ProgressDialog

   //FirebaseAuthentication
    private lateinit var firebaseAuth: FirebaseAuth
//   private latent var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //init firebaseDatabase
        firebaseAuth = FirebaseAuth.getInstance()


        //Configure actionBar
//        actionBar = supportActionBar!!
//        actionBar.title = "Sign In"

        //configure progress dialog
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("please Wait")
        progressDialog.setMessage("Logging In...")
        progressDialog.setCanceledOnTouchOutside(false)



        //Handle click,open SignUp activity
        binding.signUpButton.setOnClickListener {
            startActivity(Intent(this, SignupPage::class.java))
        }

        //Handle click, begin login
        binding.continueButton2.setOnClickListener {


            validateData()


        }

    }

    private var email = ""
    private var password = ""

    private fun validateData() {
        // ** Input data **
         email = binding.editTextUsername.text.toString().trim()
        password =  binding.editTextPassword.text.toString().trim()

        //** Validate data **
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(this, "Invalid email format..." ,Toast.LENGTH_SHORT).show()
        }
        else if (password.isEmpty()){
            //No password entered
            Toast.makeText(this, "Enter password...", Toast.LENGTH_SHORT).show()
        }
        else{
            //Data is validated, begin login
            loginUser()
        }
    }

    private fun loginUser() {
        // ** Login - Firebase Auth **

        //Show progress
        progressDialog.setMessage("Logging In...")
        progressDialog.show()

        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                //Login success
                checkUser()
        }
            .addOnFailureListener { e->
                //Failed login
                progressDialog.dismiss()
                Toast.makeText(this, "Login failed due to ${e.message}", Toast.LENGTH_LONG).show()
            }

    }

    private fun checkUser() {
        //** Check user type - Firebase Authentication **
        //If user is already logged in go to profile activity
        //Get current user

        progressDialog.setMessage("Checking user...")
        val firebaseUser = firebaseAuth.currentUser!!
        val ref = FirebaseDatabase.getInstance().getReference("Users")
        ref.child(firebaseUser.uid).addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                progressDialog.dismiss()

                //Get user type
//                val userType = snapshot.child("Users").child("userType").value
//                if (userType == "user"){
                    startActivity(Intent(this@LoginPage, ProfileActivity::class.java))
//                }

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

    }
}

