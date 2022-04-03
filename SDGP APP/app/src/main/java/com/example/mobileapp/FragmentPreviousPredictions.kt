package com.example.mobileapp

import android.app.ProgressDialog
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mobileapp.databinding.FragmentPreviousPredictionsBinding
import com.google.firebase.storage.FirebaseStorage
import java.io.File

class FragmentPreviousPredictions : Fragment() {

//    lateinit var binding: FragmentPreviousPredictionsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//        val view = inflater.inflate(R.layout.fragment_previous_predictions,container,false)
//
//
//
////    override fun onCreate(savedInstanceState: Bundle?) {
////        super.onCreate(savedInstanceState)
////        binding = FragmentPreviousPredictionsBinding.inflate(layoutInflater)
////        setContentView(binding.root)
//
//        val displayImage = view.findViewById<ImageView>(R.id.displayImage)
//
//
//        val progressDialog = ProgressDialog(requireContext())
//        progressDialog.setMessage("Fetching image...")
//        progressDialog.setCancelable(false)
//        progressDialog.show()
//
//        //watch Recycler view
//
//
//
//        val imageName = binding.getImageId.text.toString()
//        val storageRef = FirebaseStorage.getInstance().reference.child("images/$imageName.jpg")
//
//        val localFile = File.createTempFile("tempImage", "jpg")
//        storageRef.getFile(localFile).addOnSuccessListener {
//            if (progressDialog.isShowing)
//                progressDialog.dismiss()
//
//            val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
//            binding.imageView3.setImageBitmap(bitmap)
//
//        }.addOnFailureListener {
//
//            if (progressDialog.isShowing)
//                progressDialog.dismiss()
//
//            Toast.makeText(requireContext(), "Failed to retrieve the image", Toast.LENGTH_SHORT).show()
//        }
        return view
    }

}
