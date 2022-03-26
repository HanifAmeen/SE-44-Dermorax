package com.example.mobileapp

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.example.mobileapp.databinding.SignupPageBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class SignupPage : AppCompatActivity() {
    //ViewBinding
    private lateinit var binding:SignupPageBinding

    //ActionBar
//    private lateinit var actionBar:ActionBar

    //progressDialog
    private lateinit var progressDialog: ProgressDialog


    //Firebase authentication
    private lateinit var firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SignupPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Configure ActionBar, Enable back button
//        actionBar = supportActionBar!!
//        actionBar.title = "Sign Up"
//        actionBar.setDisplayHomeAsUpEnabled(true)
//        actionBar.setDisplayShowHomeEnabled(true)

        //Configure progress Dialog
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("please Wait")
        progressDialog.setMessage("Creating account...")
        progressDialog.setCanceledOnTouchOutside(false)

        //Initializing firebase auth
        firebaseAuth = FirebaseAuth.getInstance()

        //Sign In button
        binding.signInButton.setOnClickListener {
            startActivity(Intent(this, LoginPage::class.java))
        }

        //Handle click, begin signup
        binding.continueButton.setOnClickListener {

            validateData()
        }
    }

    private var username = ""
    private var email = ""
    private var password = ""

    private fun validateData() {
        // ** Input Data **
        username = binding.editTextUsername.text.toString().trim()
        email = binding.editTextEmail.text.toString().trim()
        password = binding.editTextPassword.text.toString().trim()

        // ** Validate Data **
        if (username.isEmpty()){
            //Empty name
            Toast.makeText(this, "Enter username...", Toast.LENGTH_SHORT).show()
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            //Invalid email format
            Toast.makeText(this, "Invalid Email format...", Toast.LENGTH_SHORT).show()
        }
        else if (password.isEmpty()){
            Toast.makeText(this, "Enter password...", Toast.LENGTH_SHORT).show()
        }
        else{
            createUserAccount()
        }

    }

    private fun createUserAccount() {
        // ** Create account - Firebase Auth **
        progressDialog.setMessage("Creating Account...")
        progressDialog.show()

        //Create user in firebase auth
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                //Account created and add user info in db
                updateUserInfo()
        }
            .addOnFailureListener {e->
                //Failed creating account
                progressDialog.dismiss()
                Toast.makeText(this, "Signup failed due to ${e.message}", Toast.LENGTH_LONG).show()
            }
    }

    private fun updateUserInfo() {
       // ** Save user info - firebase Realtime database **

        progressDialog.setMessage("Saving user info...")

        //Timestamp
        val timestamp = System.currentTimeMillis()
        //Get current user uid since user is registered so we can get it now
        val uid = firebaseAuth.uid

        ///Setup data to add in db
        val hashMap: HashMap<String, Any?> = HashMap()
        hashMap["email"] = email
        hashMap["uid"] = uid
        hashMap["username"] = username
        hashMap["password"] = password
        hashMap["profileImage"] = ""
        hashMap["userType"] = "user" //user/admin
        hashMap["timestamp"] = timestamp

        //Set data to db
        val ref = FirebaseDatabase.getInstance().getReference("Users")
        ref.child(username)
            .setValue(hashMap)
            .addOnSuccessListener {
                //User info saved  Open User welcome page
                progressDialog.dismiss()
                Toast.makeText(this, "Account created...", Toast.LENGTH_LONG).show()
                val profileActivity = Intent(applicationContext, ProfileActivity::class.java)
                startActivity(profileActivity)

            }
            .addOnFailureListener { e->
                //Failed adding data
                progressDialog.dismiss()
                Toast.makeText(this, "Failed saving user info due to ${e.message}", Toast.LENGTH_LONG).show()
            }



    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed() // Go back to previous activity, when back button of actionbar is clicked
        return super.onSupportNavigateUp()
    }
}




