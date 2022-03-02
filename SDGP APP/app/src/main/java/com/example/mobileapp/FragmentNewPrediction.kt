package com.example.mobileapp

import android.app.Application
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Camera
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.ToggleButton
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.theartofdev.edmodo.cropper.CropImage
import java.util.jar.Manifest

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
    private lateinit var imageView: ImageView
    private lateinit var cameraView: FrameLayout
    private lateinit var captureUploadBtn: ToggleButton
    private val cameraRequestId = 1222


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        val view = inflater.inflate(R.layout.fragment_new_prediction,container,false)

        captureUploadBtn= view.findViewById(R.id.id_uploadCaptureBtn)
        imageView= view.findViewById(R.id.id_imageView)
        cameraView= view.findViewById(R.id.id_imageView_frame)

        cropActivityResultLauncher = registerForActivityResult(cropActivityContract){
            it?.let { uri ->
                imageView.setImageURI(uri)

            }
        }

        imageView.isClickable=true
        imageView.setOnClickListener {
            val cameraInt = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraInt,cameraRequestId)
        }

        captureUploadBtn.setOnCheckedChangeListener{ _, isChecked ->

            if (isChecked){
                imageView.isClickable=false
                cropActivityResultLauncher.launch(null)
            }else{

                imageView.isClickable=true
                imageView.setOnClickListener {
                    val cameraInt = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    startActivityForResult(cameraInt,cameraRequestId)
                }

            }
        }



        return view



    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==cameraRequestId){
            val image:Bitmap=data?.extras?.get("data") as Bitmap
            imageView.setImageBitmap(image)
        }


    }




}


