package com.example.mobileapp

import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.theartofdev.edmodo.cropper.CropImage

class FragmentNewPrediction : Fragment() {

    private val cropActivityContract = object : ActivityResultContract<Any?, Uri?>(){
        override fun createIntent(context: Context, input: Any?): Intent {

            return CropImage.activity()
                .setAspectRatio(1,1)
                .getIntent(activity!!.applicationContext)
        }
        override fun parseResult(resultCode: Int, intent: Intent?): Uri? {
            return CropImage.getActivityResult(intent)?.uri
        }
    }

    private lateinit var cropActivityResultLauncher: ActivityResultLauncher<Any?>
    private lateinit var uploadImage : Button
    private lateinit var imageView: ImageView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        val view = inflater.inflate(R.layout.fragment_new_prediction,container,false)

        uploadImage= view.findViewById(R.id.id_upload)
        imageView= view.findViewById(R.id.id_imageView)

        cropActivityResultLauncher = registerForActivityResult(cropActivityContract){
            it?.let { uri ->
                imageView.setImageURI(uri)

            }
        }
        uploadImage.setOnClickListener {
            cropActivityResultLauncher.launch(null)

        }



        return view



    }




}