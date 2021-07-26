package com.catnip.todolistapp.ui.detail

import com.catnip.todolistapp.data.local.room.datasource.TodoDataSource
import com.catnip.todolistapp.data.model.Todo

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class DetailTodoRepository(private val dataSource: TodoDataSource) {

    suspend fun getTodoById(todoID : Int) : Todo{
        return dataSource.getTodoById(todoID)
    }

    suspend fun changeTodoStatus(todo: Todo): Todo {
        val updatedTodo = todo.copy().apply {
            this.isTaskCompleted = isTaskCompleted.not()
        }
        dataSource.updateTodo(updatedTodo)
        return getTodoById(todo.id)
    }

}