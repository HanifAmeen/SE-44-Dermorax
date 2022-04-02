package com.example.mobileapp

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Handler
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
import org.w3c.dom.Text
import java.io.ByteArrayOutputStream
import java.io.IOException


class FragmentNewPrediction : Fragment()  {

    //crop activity
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
    private var image:Bitmap? = null
    private lateinit var predictionValueView : TextView
    private lateinit var percentageView:TextView
    private lateinit var statusView: TextView



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
        predictionValueView= view.findViewById(R.id.id_predictionView)
        percentageView= view.findViewById(R.id.id_percentage_view)
        statusView= view.findViewById(R.id.id_status_view)



        //crop activity launcher
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

        //image is made clickable to open the camera activity
        imageView.isClickable=true

        imageView.setOnClickListener {
            val cameraInt = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraInt,cameraRequestId)
        }


        //toggle button (upload and capture image)
        captureUploadBtn.setOnCheckedChangeListener{ _, isChecked ->
            //upload
            image=null
            if (isChecked){
                imageView.isClickable=false
                imageView.setImageDrawable(null)
                cropActivityResultLauncher.launch(null)
            }else{
                //camera capture
                imageView.setImageResource(R.drawable.camera_bk2)
                imageView.isClickable=true
                imageView.setOnClickListener {
                    val cameraInt = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    startActivityForResult(cameraInt,cameraRequestId)
                }

            }
        }

        //back button/arrow at the top
        backBtn.setOnClickListener {
            val transaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.fragment_container,homeFragment)
            transaction.commit()
        }

        //Log.d("my","debug")

        //dialog box
        //mainly made it to take the application context ang gve it to the model
        val dialogPopup = Dialog(requireContext())
        dialogPopup.setContentView(R.layout.activity_dialog)

        //prediction button
        predictBtn.setOnClickListener {

            if (image==null){

                Toast.makeText(dialogPopup.context,"Please upload an image to predict",Toast.LENGTH_SHORT).show()

            }else {
                predictBtn.text = "Processing......"



                //sleeping the thread for one milli second to give the order of execution for the handler
                Handler().postDelayed({
                    //Convert the bitmap to byte array
                    val stream = ByteArrayOutputStream()
                    image?.compress(Bitmap.CompressFormat.PNG, 100, stream)
                    val byteArray: ByteArray = stream.toByteArray()

                    val model = Model(dialogPopup.context)
                    val predictionArray = model.main(byteArray)

                    val prediction = predictionArray[0]
                    val percentage = predictionArray[1]

                    predictBtn.text = "P R E D I C T"
                    predictionValueView.text = convertToText(prediction)
                    statusView.text = detectCancerousOrNot(prediction)
                    percentageView.text = "$percentage%"

                }, 1)

                dialogPopup.show()
                val xClose = dialogPopup.findViewById<TextView>(R.id.id_close)
                xClose.setOnClickListener {
                    dialogPopup.dismiss() //closes the popup
                }


            }

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

    private fun convertToText(code:String) : String{
        var text =""

        if (code=="akiec") text="Actinic Keratosis (AKIEC)"
        else if (code=="bcc") text="Basal-cell carcinoma (BCC)"
        else if (code=="bkl") text="Benign keratosis-like lesion (bkl) "
        else if (code=="df") text="Dermatofibroma (DF)"
        else if (code=="mel") text="Melanoma (MEL)"
        else if (code=="nv") text="Melanocytic nevi (NV)"
        else if (code=="vasc") text="Vascular Lesion (VASC)"

        return text
    }

    private fun detectCancerousOrNot(code:String): String{
        var text =""

        if (code=="akiec") text="CANCEROUS"
        else if (code=="bcc") text="CANCEROUS"
        else if (code=="bkl") text="NON-CANCEROUS"
        else if (code=="df") text="CANCEROUS"
        else if (code=="mel") text="CANCEROUS"
        else if (code=="nv") text="NON-CANCEROUS"
        else if (code=="vasc") text="NON-CANCEROUS"

        return text
    }




}


