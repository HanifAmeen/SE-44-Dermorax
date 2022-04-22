package com.example.mobileapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentController
import androidx.fragment.app.FragmentManager
import kotlin.system.exitProcess

class FragmentHome : Fragment() {


    private lateinit var newPredictionBtn : Button
    private lateinit var previousPredictionsBtn : Button
    private lateinit var newsFeedBtn : Button
    private lateinit var exitBtn : Button


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home,container,false)

        val fragmentNewPrediction = FragmentNewPrediction()
        val fragmentPreviousPrediction = FragmentPreviousPredictions()
        val fragmentNews = FragmentNews()

        newPredictionBtn = view.findViewById(R.id.id_btn_new_prediction)
        previousPredictionsBtn= view.findViewById(R.id.id_btn_previous_predictions)
        newsFeedBtn= view.findViewById(R.id.id_btn_news_feed)
        exitBtn= view.findViewById(R.id.id_btn_exit)

        newPredictionBtn.setOnClickListener {
            replaceFragment(fragmentNewPrediction)
        }
        previousPredictionsBtn.setOnClickListener {
            replaceFragment(fragmentPreviousPrediction)
        }
        newsFeedBtn.setOnClickListener {
            replaceFragment(fragmentNews)
        }
        exitBtn.setOnClickListener {
            android.os.Process.killProcess(android.os.Process.myPid())
            exitProcess(2)

        }



        return view
    }

    //fragment to be show is taken as parameter and is replaced in the fragment container
    private fun replaceFragment(fragment: Fragment): Boolean {
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container,fragment)
        transaction.commit()

        return true
    }

}