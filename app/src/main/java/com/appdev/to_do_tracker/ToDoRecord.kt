package com.appdev.to_do_tracker

data class ToDoRecord(
    val todoTitle: String?,
    val isComplete: Boolean?,
    val priority: String?,
    val deadlineDay: Int?,
    val deadlineMonth: Int?,
    val deadlineYear: Int?,
    val shouldRemind: Boolean?,
    val storedID: Long = 0
) : java.io.Serializable