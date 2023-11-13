package com.appdev.to_do_tracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    private val currentDate: Calendar = Calendar.getInstance()
    private val pastDueRecords = mutableListOf<ToDoRecord>()
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
            (application as ToDoApplication).db.todoDao().getRecordsBeforeDate(currentDate.get(Calendar.DAY_OF_MONTH), currentDate.get(Calendar.MONTH)+1, currentDate.get(Calendar.YEAR)).collect { databaseList ->
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
                    pastDueRecords.clear()
                    pastDueRecords.addAll(mappedList)
                }
            }
        }

        val pastDueFrag: Fragment = PastDueFragment(this, pastDueRecords)
        // filterDay, filterMonth, filterYear are passed to homeFrag where the database pull is made.
        val homeFrag: Fragment = HomeFragment(this, filterDay, filterMonth, filterYear)
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
        val buttonLaunch = findViewById<Button>(R.id.addBtn)
        buttonLaunch.setOnClickListener {
            val intent = Intent(this,AddItemActivity::class.java)
            this.startActivity(intent)
        }
    }

    private fun replaceFragment(newFragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.main_frame_layout, newFragment)
        fragmentTransaction.commit()
    }
}