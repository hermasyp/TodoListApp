package com.catnip.todolistapp.ui.todoform

import com.catnip.todolistapp.base.BasePresenter
import com.catnip.todolistapp.data.local.room.datasource.TaskDataSource
import com.catnip.todolistapp.data.model.Todo
import kotlinx.coroutines.launch

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class TodoFormPresenter(
    private val dataSource: TaskDataSource,
    private val view: TodoFormContract.View
) : BasePresenter(), TodoFormContract.Presenter {
    override fun insertTodo(todo: Todo) {
        scope.launch {
            try {
                val todoId = dataSource.addTodo(todo)
                if (todoId > 0) {
                    view.onSuccess()
                } else {
                    view.onFailed()
                }
            } catch (e: Exception) {
                view.onFailed()
            }
        }
    }

    override fun updateTodo(todo: Todo) {
        scope.launch {
            try {
                val todoId = dataSource.updateTodo(todo)
                if (todoId > 0) {
                    view.onSuccess()
                } else {
                    view.onFailed()
                }
            } catch (e: Exception) {
                view.onFailed()
            }
        }
    }
}