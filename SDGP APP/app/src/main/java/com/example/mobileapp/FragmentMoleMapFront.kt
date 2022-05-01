package com.example.mobileapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment

class FragmentMoleMapFront : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_mole_map_front,container,false)

        val button1 = view.findViewById<Button>(R.id.back_view_button)
        val fragmentMoleMapBack = FragmentMoleMapBack()
        button1.setOnClickListener{
            replaceFragment(fragmentMoleMapBack)

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
