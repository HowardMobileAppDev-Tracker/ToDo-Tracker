package com.appdev.to_do_tracker

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_records_table")
data class ToDoRecordEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "todoTitle") val todoTitle: String?,
    @ColumnInfo(name = "isComplete") val isComplete: Boolean?,
    @ColumnInfo(name = "deadlineDay") val deadlineDay: Int?,
    @ColumnInfo(name = "deadlineMonth") val deadlineMonth: Int?,
    @ColumnInfo(name = "deadlineYear") val deadlineYear: Int?,
    @ColumnInfo(name = "shouldRemind") val shouldRemind: Boolean?
)