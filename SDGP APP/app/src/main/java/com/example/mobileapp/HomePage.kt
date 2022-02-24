package com.example.mobileapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomePage : AppCompatActivity() {

    private val fragmentHome = FragmentHome()
    private val fragmentNewPrediction = FragmentNewPrediction()
    private val fragmentPreviousPrediction = FragmentPreviousPredictions()
    private val fragmentNews = FragmentNews()
    private val fragmentMoleMap = FragmentMoleMap()
    private val fragmentChatBot = FragmentChatBot()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_page)

        val navBar : BottomNavigationView  = findViewById(R.id.bottom_nav_view)
        val floatAddButton : FloatingActionButton = findViewById(R.id.floating_add_button)

        replaceFragment(fragmentHome)


        floatAddButton.setOnClickListener{
            replaceFragment(fragmentNewPrediction)
        }


        navBar.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_news_feed -> replaceFragment(fragmentNews)
                R.id.nav_mole_mapper -> replaceFragment(fragmentMoleMap)
                R.id.nav_chatbot -> replaceFragment(fragmentChatBot)
                R.id.nav_previous_predictions -> replaceFragment(fragmentPreviousPrediction)

                else -> {replaceFragment(fragmentHome)}
            }
        }


    }

    private fun replaceFragment(fragment: Fragment): Boolean {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container,fragment)
        transaction.commit()

        return true
    }

}