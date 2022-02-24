package com.example.mobileapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.core.content.withStyledAttributes
import com.theartofdev.edmodo.cropper.CropImage

class UserWelcome : AppCompatActivity() {

    private val cropActivityContract = object : ActivityResultContract<Any?, Uri?>(){
        override fun createIntent(context: Context, input: Any?): Intent {
            return CropImage.activity()
                .setAspectRatio(16,8)
                .getIntent(this@UserWelcome)
        }
        override fun parseResult(resultCode: Int, intent: Intent?): Uri? {
            return CropImage.getActivityResult(intent)?.uri
        }
    }

    private lateinit var cropActivityResultLauncher: ActivityResultLauncher<Any?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_welcome)

        val getStartedBtn= findViewById<Button>(R.id.getStartedButton)
        val dpEditBtn = findViewById<Button>(R.id.id_user_welcm_dp_edit)
        val dpView = findViewById<ImageView>(R.id.userDp)
        dpView.setImageResource(R.drawable.user_dp)

        cropActivityResultLauncher = registerForActivityResult(cropActivityContract){
            it?.let { uri ->
                dpView.setImageURI(uri)

            }
        }
        dpEditBtn.setOnClickListener {
            cropActivityResultLauncher.launch(null)

        }

        getStartedBtn.setOnClickListener {
            val homeIntent = Intent(this,HomePage::class.java)
            startActivity(homeIntent)
        }
    }
}