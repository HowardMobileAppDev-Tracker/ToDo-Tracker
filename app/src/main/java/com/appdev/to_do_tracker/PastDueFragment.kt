package com.appdev.to_do_tracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


/**
 * A simple [Fragment] subclass.
 * Use the [PastDueFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PastDueFragment(private val activity: MainActivity, private val toDoRecord: List<ToDoRecord>): Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)
        if (toDoRecord.isNotEmpty()){
            val recyclerView = view.findViewById(R.id.itemRv) as RecyclerView
            val adapter = ItemListAdapter(activity, toDoRecord)
            val context = view.context
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(context)
        }else{
            return view
        }
        return view
    }

}