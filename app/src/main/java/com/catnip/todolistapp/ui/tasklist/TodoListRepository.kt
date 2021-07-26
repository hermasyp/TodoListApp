package com.catnip.todolistapp.ui.tasklist

import com.catnip.todolistapp.data.local.room.datasource.TodoDataSource
import com.catnip.todolistapp.data.model.Todo

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class TodoListRepository(private val dataSource: TodoDataSource) {

    suspend fun getTodoByCompleteness(isTaskCompleted: Boolean): List<Todo> {
        return dataSource.getTodoByCompleteness(isTaskCompleted)
    }

    suspend fun deleteTodo(todo: Todo) : Int {
        return dataSource.deleteTodo(todo)
    }

}