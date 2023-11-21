package com.appdev.to_do_tracker

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ItemListAdapter (private val activity: MainActivity, private val toDoRecord: List<ToDoRecord>)
    : RecyclerView.Adapter<ItemListAdapter.TodoViewHolder>(){
    class TodoViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val todoTitle: TextView = mView.findViewById<View>(R.id.complete) as TextView
        val dueDate: TextView = mView.findViewById<View>(R.id.due) as TextView
        val checkBoxView: CheckBox = mView.findViewById<View>(R.id.complete) as CheckBox
        val priorityView: TextView = mView.findViewById<View>(R.id.itemPriority) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return TodoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return toDoRecord.size
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val record = toDoRecord[position]
        holder.todoTitle.text = record.todoTitle
        val dateString = "${record.deadlineMonth}/${record.deadlineDay}/${record.deadlineYear}"
        holder.dueDate.text = dateString
        when (record.priority) {
            "Low" -> {
                holder.priorityView.text = "Low"
                holder.priorityView.setTextColor(Color.GREEN)
            }
            "Medium" -> {
                holder.priorityView.text = "Medium"
                holder.priorityView.setTextColor(Color.BLUE)
            }
            "High" -> {
                holder.priorityView.text = "High"
                holder.priorityView.setTextColor(Color.RED)
            }
        }
        holder.checkBoxView.isChecked = record.isComplete == true // set box to checked if checked in database.

        holder.checkBoxView.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                // write state to database
                setRecordState(record, true)
            } else {
                // write state to database
                setRecordState(record, false)
            }
        }

    }

    private fun setRecordState(todoObject: ToDoRecord, newState: Boolean) {
        GlobalScope.launch {
            (activity.application as ToDoApplication).db.todoDao().setNewState(todoObject.storedID, newState)
        }
    }

}