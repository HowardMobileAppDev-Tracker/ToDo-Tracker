package com.appdev.to_do_tracker

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDAO {
    @Query("SELECT * FROM todo_records_table")
    fun getAll(): Flow<List<ToDoRecordEntity>>

    @Insert
    fun insert(todoRecord: ToDoRecordEntity)
}