package com.example.mobileapp

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.jar.Manifest

open class HomePage : AppCompatActivity() {

    private val fragmentHome = FragmentHome()
    private val fragmentNewPrediction = FragmentNewPrediction()
    private val fragmentPreviousPrediction = FragmentPreviousPredictions()
    private val fragmentNews = FragmentNews()
    private val fragmentMoleMap = FragmentMoleMap()
    private val fragmentChatBot = FragmentChatBot()
    private var cameraAccessPermission = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_page)


        val navBar : BottomNavigationView  = findViewById(R.id.bottom_nav_view)
        val floatAddButton : FloatingActionButton = findViewById(R.id.floating_add_button)

        //initially home fragment is loaded to the fragment container
        replaceFragment(fragmentHome)

        //float button for new predictions
        //mandatory to allow camera permission inorder to launch the new prediction activity
        floatAddButton.setOnClickListener{
            getCameraPermissionLoadFragment()
        }

        //navigation bar listener - replace with specific fragments
        navBar.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_news_feed -> replaceFragment(fragmentNews)
                R.id.nav_mole_mapper -> replaceFragment(fragmentMoleMap)
                R.id.nav_home -> replaceFragment(fragmentChatBot)
                R.id.nav_previous_predictions -> replaceFragment(fragmentPreviousPrediction)

                else -> {replaceFragment(fragmentHome)}
            }
        }


    }

    //fragment to be show is taken as parameter and is replaced in the fragment container
    private fun replaceFragment(fragment: Fragment): Boolean {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container,fragment)
        transaction.commit()

        return true
    }

    //if camera permission is denied it asks for permission again
    //else permission granted it loads the new prediction activity
    private fun getCameraPermissionLoadFragment(){

        if (ContextCompat.checkSelfPermission(applicationContext, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA),1222)

        } else{
            replaceFragment(fragmentNewPrediction)
        }

    }

    //captures the permissions with the request code
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        //only executes it the request code is equal to camera requestID
        if (requestCode == 1222) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                replaceFragment(fragmentNewPrediction)
            }
            else {
                Toast.makeText(applicationContext, "Camera Permission is needed to proceed", Toast.LENGTH_SHORT).show()
            }
        }
    }

}