package com.appdev.to_do_tracker

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemListAdapter (private val toDoRecord: List<ToDoRecord>)
    : RecyclerView.Adapter<ItemListAdapter.TodoViewHolder>(){
    class TodoViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val todoTitle: TextView = mView.findViewById<View>(R.id.complete) as TextView
        val dueDate: TextView = mView.findViewById<View>(R.id.due) as TextView
        val checkBoxView: CheckBox = mView.findViewById<View>(R.id.complete) as CheckBox
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
        holder.checkBoxView.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                Log.v("Item Checked", "User just clicked on an item. Delete from database")
            }
        }

    }

    // TODO: Complete implementation of the delete function.
    fun deleteRecord(todoObject: ToDoRecord) {}

}