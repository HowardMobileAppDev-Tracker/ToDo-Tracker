package com.appdev.to_do_tracker

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDAO {
    @Query("SELECT * FROM todo_records_table")
    fun getAll(): Flow<List<ToDoRecordEntity>>

    @Insert
    fun insert(todoRecord: ToDoRecordEntity)

    @Query("DELETE FROM todo_records_table WHERE id=:todoEntityID")
    fun delete(todoEntityID: Long)

    @Query("SELECT * FROM todo_records_table WHERE deadlineDay=:filterDay AND deadlineMonth=:filterMonth AND deadlineYear=:filterYear")
    fun getRecordsByDate(filterDay: Int, filterMonth: Int, filterYear: Int): Flow<List<ToDoRecordEntity>>

    @Query("SELECT * FROM todo_records_table WHERE deadlineDay<:filterDay AND deadlineMonth<=:filterMonth AND deadlineYear<=:filterYear AND isComplete=:isComplete")
    fun getRecordsBeforeDate(filterDay: Int, filterMonth: Int, filterYear: Int, isComplete: Boolean = false): Flow<List<ToDoRecordEntity>>

    @Query("UPDATE todo_records_table SET isComplete=:newState WHERE id=:todoEntityID")
    fun setNewState(todoEntityID: Long, newState: Boolean)
}