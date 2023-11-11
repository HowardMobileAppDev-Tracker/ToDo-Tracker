package com.appdev.to_do_tracker

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CalendarView
import java.util.Calendar


/**
 * A Fragment class for the Calendar Filter view of the app.
 */
class CalendarFragment : Fragment() {
    private val currentDate: Calendar = Calendar.getInstance()
    private lateinit var calInputView: CalendarView
    private lateinit var filterButton: Button
    private var filterDay: Int = currentDate.get(Calendar.DAY_OF_MONTH)
    private var filterMonth: Int = currentDate.get(Calendar.MONTH) + 1
    private var filterYear: Int = currentDate.get(Calendar.YEAR)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calendar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        calInputView = view.findViewById(R.id.calFilter)
        filterButton = view.findViewById(R.id.filterSubmitBtn)

        calInputView.setOnDateChangeListener { calView, year, month, dayOfMonth ->
            filterDay = dayOfMonth
            filterMonth = month+1
            filterYear = year
        }

        filterButton.setOnClickListener {
            // pull necessary records from database and pass to Home, or pass date to Home and pull in Home.
            Log.v("Calendar Listener", "Chosen date is $filterMonth/$filterDay/$filterYear")
        }
    }

}