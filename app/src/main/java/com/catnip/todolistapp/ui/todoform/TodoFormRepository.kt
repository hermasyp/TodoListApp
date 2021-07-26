package com.catnip.todolistapp.ui.todoform

import com.catnip.todolistapp.data.local.room.datasource.TodoDataSource
import com.catnip.todolistapp.data.model.Todo

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class TodoFormRepository(private val dataSource: TodoDataSource) {
    suspend fun addTodo(todo: Todo): Long {
        return dataSource.addTodo(todo)
    }
    suspend fun updateTodo(todo: Todo): Int {
        return dataSource.updateTodo(todo)
    }
}