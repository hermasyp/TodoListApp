package com.catnip.todolistapp.ui.todoform

import com.catnip.todolistapp.base.BasePresenterImpl
import com.catnip.todolistapp.data.local.room.datasource.TodoDataSource
import com.catnip.todolistapp.data.model.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class TodoFormPresenter(
    private val dataSource: TodoDataSource,
    private val view: TodoFormContract.View
) : BasePresenterImpl(), TodoFormContract.Presenter {
    override fun insertTodo(todo: Todo) {
        scope.launch {
            try {
                val todoId = dataSource.addTodo(todo)
                scope.launch (Dispatchers.Main){
                    if (todoId > 0) {
                        view.onSuccess()
                    } else {
                        view.onFailed()
                    }
                }
            } catch (e: Exception) {
                scope.launch (Dispatchers.Main){
                    view.onFailed()
                }
            }
        }
    }

    override fun updateTodo(todo: Todo) {
        scope.launch {
            try {
                val todoId = dataSource.updateTodo(todo)
                scope.launch (Dispatchers.Main){
                    if (todoId > 0) {
                        view.onSuccess()
                    } else {
                        view.onFailed()
                    }
                }
            } catch (e: Exception) {
                scope.launch (Dispatchers.Main){
                    view.onFailed()
                }
            }
        }
    }
}