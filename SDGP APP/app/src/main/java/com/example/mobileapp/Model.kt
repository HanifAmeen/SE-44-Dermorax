package com.example.mobileapp

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mobileapp.ml.Irv2Sa
import org.tensorflow.lite.DataType
import org.tensorflow.lite.Interpreter
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.io.IOException
import java.nio.ByteBuffer
import java.nio.ByteOrder

class Model(private val context: Context) : AppCompatActivity() {

    private lateinit var resizeImage : Bitmap
    //private lateinit var image: Bitmap
    private lateinit var prediction: String
    //private lateinit var tflite: Interpreter

    var skinCancerList = listOf ("akiec", "bcc", "bkl", "df", "mel", "nv", "vasc")


    fun main(byteArray: ByteArray) : String{


        //initializing the elements
//        predictionValueView= findViewById(R.id.id_predictionValue)
      //  predictionView= findViewById(R.id.id_predictionView)

        //retrieving the byte array from the intent and converting it to a bitmap

        val image = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)

        resizeImage = Bitmap.createScaledBitmap(image, 299, 299, true)




        //     Log.d("my", resizeImage.height.toString())
        //    Log.d("my", resizeImage.width.toString())

        //model

        try {
            val model = Irv2Sa.newInstance(context)

//            if (model == null) Log.d("my2", "model is null")
            // Creates inputs for reference.
            val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 299, 299, 3), DataType.FLOAT32)

//            Log.d("my", inputFeature0.shape.toString())

            //creating a tensor buffer
//            val tensorBuffer = TensorImage.fromBitmap(resizeImage)
            //converting to a byte buffer
//                val byteBuffer = tensorBuffer.buffer

            val byteBuffer: ByteBuffer = ByteBuffer.allocateDirect(4 * 299 * 299 * 3)
            byteBuffer.order(ByteOrder.nativeOrder())

            val intValues = IntArray(299 * 299)
            resizeImage.getPixels(intValues, 0, resizeImage.width, 0, 0, resizeImage.width, resizeImage.height)
            var pixel = 0
            for (i in 0..298) {
                for (j in 0..298) {
                    val `val` = intValues[pixel++]
                    byteBuffer.putFloat((`val` shr 16 and 0xFF) * (1f / 255f))
                    byteBuffer.putFloat((`val` shr 8 and 0xFF) * (1f / 255f))
                    byteBuffer.putFloat((`val` and 0xFF) * (1f / 255f))
                }
            }

            //    Log.d("my", tensorBuffer.width.toString())
            //     Log.d("my", tensorBuffer.height.toString())


            inputFeature0.loadBuffer(byteBuffer)

            // Runs model inference and gets result.
            val outputs = model.process(inputFeature0)
            val outputFeature0 = outputs.outputFeature0AsTensorBuffer  //predictions float array

            val predictionIndex = getMaxPredictionValueIndex(outputFeature0.floatArray)


            //      Log.d("my2", "prediction index $predictionIndex")
            //      Log.d("my2", outputFeature0.floatArray.toString())

//            predictionView.text="done"
            prediction= skinCancerList[predictionIndex]


            // Releases model resources if no longer used.
            model.close()


        }catch (e : IOException){
            e.printStackTrace()
        }

        return prediction
    }

    //returns the index from the array which has the maximum prediction value
    private fun getMaxPredictionValueIndex(arr: FloatArray) : Int{

        var index=0
        var max = 0.00f

        for (i in arr.indices){
            if (arr[i]>max){
                max = arr[i]
                index=i
            }
        }

        return index
    }


}