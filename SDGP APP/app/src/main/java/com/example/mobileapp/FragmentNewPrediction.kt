package com.example.mobileapp

import android.Manifest.permission.CAMERA
import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
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
import androidx.core.view.setPadding
import androidx.fragment.app.Fragment
import com.theartofdev.edmodo.cropper.CropImage
import java.util.jar.Manifest

class FragmentNewPrediction : Fragment()  {

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
    private lateinit var backBtn: Button
    private lateinit var predictBtn: Button
    private var homeFragment = FragmentHome()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        val view = inflater.inflate(R.layout.fragment_new_prediction,container,false)

        captureUploadBtn= view.findViewById(R.id.id_uploadCaptureBtn)
        imageView= view.findViewById(R.id.id_imageView)
        cameraView= view.findViewById(R.id.id_imageView_frame)
        backBtn= view.findViewById(R.id.id_back_btn)
        predictBtn= view.findViewById(R.id.id_predict_btn)

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
                imageView.setImageDrawable(null)
                cropActivityResultLauncher.launch(null)
            }else{

                imageView.setImageResource(R.drawable.camera_bk2)
                imageView.isClickable=true
                imageView.setOnClickListener {
                    val cameraInt = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    startActivityForResult(cameraInt,cameraRequestId)
                }

            }
        }

        backBtn.setOnClickListener {
            val transaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.fragment_container,homeFragment)
            transaction.commit()
        }

        predictBtn.setOnClickListener {

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


