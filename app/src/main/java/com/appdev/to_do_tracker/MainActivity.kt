package com.appdev.to_do_tracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    private val currentDate: Calendar = Calendar.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Date to pull data to show in the Home page. Set to Today's date by default. Reassigned
        // from Calendar view.
        var filterDay = intent.getIntExtra("DayToShow", currentDate.get(Calendar.DAY_OF_MONTH))
        var filterMonth = intent.getIntExtra("MonthToShow", currentDate.get(Calendar.MONTH)+1)
        var filterYear = intent.getIntExtra("YearToShow", currentDate.get(Calendar.YEAR))

        // Log.v("MainActivityTag", "Chosen date is $filterMonth/$filterDay/$filterYear")

        val pastDueFrag: Fragment = PastDueFragment()
        val homeFrag: Fragment = HomeFragment()
        val calendarFilterFrag: Fragment = CalendarFragment()

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavigationView.setOnItemSelectedListener { item ->
            lateinit var fragment: Fragment
            when (item.itemId) {
                R.id.nav_late -> fragment = pastDueFrag
                R.id.nav_home -> fragment = homeFrag
                R.id.nav_calendar -> fragment = calendarFilterFrag
            }
            replaceFragment(fragment)
            true
        }
        bottomNavigationView.selectedItemId = R.id.nav_home
    }

    private fun replaceFragment(newFragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.main_frame_layout, newFragment)
        fragmentTransaction.commit()
    }
}