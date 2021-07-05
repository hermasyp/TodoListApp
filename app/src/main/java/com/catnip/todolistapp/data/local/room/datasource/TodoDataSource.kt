package com.catnip.todolistapp.data.local.room.datasource

import com.catnip.todolistapp.data.local.room.dao.TodoDao
import com.catnip.todolistapp.data.model.Todo

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class TodoDataSource(private val todoDao: TodoDao) {

    suspend fun getTodoById(todoID : Int) : Todo{
        return todoDao.getTodoById(todoID)
    }

    suspend fun addTodo(todo: Todo): Long {
        return todoDao.insertTodo(todo)
    }

    suspend fun deleteTodo(todo: Todo) {
        todoDao.deleteTodo(todo)
    }

    suspend fun updateTodo(todo: Todo): Int {
        return todoDao.updateTodo(todo)
    }

    suspend fun changeTodoStatus(todo: Todo): Todo {
        val updatedTodo = todo.copy().apply {
            this.isTaskCompleted = isTaskCompleted.not()
        }
        todoDao.updateTodo(updatedTodo)
        return getTodoById(todo.id)
    }

    suspend fun getTodoByCompleteness(isTaskCompleted: Boolean): List<Todo> {
        return todoDao.getTodoByCompleteness(isTaskCompleted)
    }


}