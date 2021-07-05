package com.catnip.todolistapp.ui.tasklist

import com.catnip.todolistapp.base.BasePresenter
import com.catnip.todolistapp.data.local.room.datasource.TodoDataSource
import com.catnip.todolistapp.data.model.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class TodoListPresenter(
    private val dataSource: TodoDataSource,
    private val view: TodoListContract.View
) :
    BasePresenter(),
    TodoListContract.Presenter {

    override fun getTodosByCompleteness(isTaskComplete: Boolean) {
        view.setLoadingStatus(true)
        scope.launch {
            try {
                val todos = dataSource.getTodoByCompleteness(isTaskComplete)
                scope.launch (Dispatchers.Main){
                    if (!todos.isNullOrEmpty()) {
                        view.onDataSuccess(todos)
                    } else {
                        view.onDataEmpty()
                    }
                    view.setLoadingStatus(false)
                }
            } catch (e: Exception) {
                scope.launch (Dispatchers.Main){
                    view.onDataFailed(e.message)
                    view.setLoadingStatus(false)
                }
            }
        }
    }

    override fun deleteTodo(todo: Todo) {
        view.setLoadingStatus(true)
        scope.launch {
            try {
                val todos = dataSource.deleteTodo(todo)
                scope.launch (Dispatchers.Main){
                    if (!todos.equals(1)) {
                        view.onDeleteDataSuccess()
                    } else {
                        view.onDeleteDataFailed()
                    }
                    view.setLoadingStatus(false)
                }
            } catch (e: Exception) {
                scope.launch (Dispatchers.Main){
                    view.onDeleteDataFailed()
                    view.setLoadingStatus(false)
                }
            }
        }
    }


}