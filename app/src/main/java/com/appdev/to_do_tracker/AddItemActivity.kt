package com.appdev.to_do_tracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.util.Calendar

class AddItemActivity : AppCompatActivity() {
    private lateinit var Titleinputview:EditText
    private lateinit var Calenderinputview: CalendarView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)

        val currentDate: Calendar = Calendar.getInstance()
        var deadlineDay: Int = currentDate.get(Calendar.DAY_OF_MONTH)
        var deadlineMonth: Int = currentDate.get(Calendar.MONTH) + 1
        var deadlineYear: Int = currentDate.get(Calendar.YEAR)


        Titleinputview = findViewById(R.id.Titleinput)
        Calenderinputview = findViewById(R.id.calendarView)

        val saveRecordBtn = findViewById<Button>(R.id.button)
        var todoRecord: ToDoRecord? = null

        Calenderinputview.setOnDateChangeListener { calView, year, month, dayOfMonth ->
            deadlineDay = dayOfMonth
            deadlineMonth = month+1
            deadlineYear = year
        }

        saveRecordBtn.setOnClickListener {
            todoRecord = ToDoRecord(
                Titleinputview.text.toString(),
                false,
                deadlineDay,
                deadlineMonth,
                deadlineYear,
                false
            )

            todoRecord?.let { obj ->
                lifecycleScope.launch(IO) {
                    (application as ToDoApplication).db.todoDao().insert(
                        ToDoRecordEntity(
                            todoTitle = obj.todoTitle,
                            isComplete = obj.isComplete,
                            deadlineDay = obj.deadlineDay,
                            deadlineMonth = obj.deadlineMonth,
                            deadlineYear = obj.deadlineYear,
                            shouldRemind = obj.shouldRemind
                        )
                    )
                }
            }

            // launch main activity
            val intent = Intent(this, MainActivity::class.java)
            this.startActivity(intent)
        }

    }
}