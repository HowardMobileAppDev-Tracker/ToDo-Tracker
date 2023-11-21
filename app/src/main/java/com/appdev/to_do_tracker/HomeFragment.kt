package com.appdev.to_do_tracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

/**
 * A Fragment class for the Home Page of the app.
 */

class HomeFragment(private val activity: MainActivity, private val filterDay: Int, private val filterMonth: Int, private val filterYear: Int) : Fragment() {
    private val todoRecords = mutableListOf<ToDoRecord>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val recyclerView = view.findViewById(R.id.itemRvHome) as RecyclerView
        val adapter = ItemListAdapter(activity, todoRecords)
        val context = view.context
        val selectedDateView = view.findViewById<TextView>(R.id.selectedDate)
        selectedDateView.text = "$filterMonth/$filterDay/$filterYear"
        val priorityOrder = mapOf("High" to 0, "Medium" to 1, "Low" to 2)

        lifecycleScope.launch {
            (activity.application as ToDoApplication).db.todoDao().getRecordsByDate(filterDay, filterMonth, filterYear).collect { databaseList ->
                databaseList.map { entity ->
                    ToDoRecord(
                        entity.todoTitle,
                        entity.isComplete,
                        entity.priority,
                        entity.deadlineDay,
                        entity.deadlineMonth,
                        entity.deadlineYear,
                        entity.shouldRemind,
                        entity.id
                    )
                }.also { mappedList ->
                    todoRecords.clear()
                    todoRecords.addAll(mappedList.sortedBy { priorityOrder[it.priority] })
                    adapter.notifyDataSetChanged()
                }
            }
        }

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        return view
    }

}