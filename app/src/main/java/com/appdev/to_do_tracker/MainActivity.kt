package com.appdev.to_do_tracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    private val currentDate: Calendar = Calendar.getInstance()
    private val todoRecords = mutableListOf<ToDoRecord>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Date to pull data to show in the Home page. Set to Today's date by default. Reassigned
        // from Calendar view.
        val filterDay = intent.getIntExtra("DayToShow", currentDate.get(Calendar.DAY_OF_MONTH))
        val filterMonth = intent.getIntExtra("MonthToShow", currentDate.get(Calendar.MONTH)+1)
        val filterYear = intent.getIntExtra("YearToShow", currentDate.get(Calendar.YEAR))

        // Log.v("MainActivityTag", "Chosen date is $filterMonth/$filterDay/$filterYear")

        lifecycleScope.launch {
            (application as ToDoApplication).db.todoDao().getRecordsByDate(filterDay, filterMonth, filterYear).collect { databaseList ->
                databaseList.map { entity ->
                    ToDoRecord(
                        entity.todoTitle,
                        entity.isComplete,
                        entity.deadlineDay,
                        entity.deadlineMonth,
                        entity.deadlineYear,
                        entity.shouldRemind
                    )
                }.also { mappedList ->
                    todoRecords.clear()
                    todoRecords.addAll(mappedList)
                }
            }
        }

        val pastDueFrag: Fragment = PastDueFragment()
        // todoRecords is passed into homeFrag so homeFrag can handle displaying each ToDoRecord object.
        val homeFrag: Fragment = HomeFragment(todoRecords)
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