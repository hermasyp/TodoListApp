package com.catnip.todolistapp.data.local.room.dao

import androidx.room.*
import com.catnip.todolistapp.data.model.Todo

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
@Dao
interface TodoDao {
    @Query("SELECT * FROM todo WHERE is_task_complete == :isTaskComplete")
    suspend fun getTodoByCompleteness(isTaskComplete : Boolean) : List<Todo>

    @Query("SELECT * FROM todo WHERE id == :id")
    suspend fun getTodoById(id : Int) : Todo

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: Todo) : Long

    @Update
    suspend fun updateTodo(todo: Todo) : Int

    @Delete
    suspend fun deleteTodo(todo: Todo)
}