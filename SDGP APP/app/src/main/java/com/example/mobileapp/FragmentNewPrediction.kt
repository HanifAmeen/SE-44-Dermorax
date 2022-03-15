package com.example.mobileapp

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.fragment.app.Fragment
import com.theartofdev.edmodo.cropper.CropImage
import java.io.ByteArrayOutputStream
import java.io.IOException


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
    private lateinit var image:Bitmap
    private lateinit var predictionValueView : TextView



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
        predictionValueView= view.findViewById(R.id.id_predictionValue)


        cropActivityResultLauncher = registerForActivityResult(cropActivityContract){
            it?.let { uri ->
                imageView.setImageURI(uri)


                try {
                    image = MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, uri)
                } catch (e: IOException) {
                    e.printStackTrace()
                }

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

        Log.d("my","debug")

        predictBtn.setOnClickListener {

            //Convert the bitmap to byte array
            val stream = ByteArrayOutputStream()
            image.compress(Bitmap.CompressFormat.PNG, 100, stream)
            val byteArray: ByteArray = stream.toByteArray()

            val modelIntent = Intent(context,ModelActivity::class.java)
            modelIntent.putExtra("Image",byteArray) //adding the byte array to the intent
            startActivity(modelIntent)


        }



        return view

    }

    //called when an activity opens
    //if request code is equal to camera ID then data from extras is collected and converted to a Bitmap
    //Bitmap image will be set to the image view
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==cameraRequestId){
            image =data?.extras?.get("data") as Bitmap

            imageView.setImageBitmap(image)

        }

    }




}


