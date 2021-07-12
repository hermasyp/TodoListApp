package com.catnip.todolistapp.ui.tasklist

import com.catnip.todolistapp.base.BasePresenterImpl
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
) : BasePresenterImpl(), TodoListContract.Presenter {

    override fun getTodoByCompleteness(isTaskComplete: Boolean) {
        view.setLoadingStatus(true)
        scope.launch {
            try {
                val todos = dataSource.getTodoByCompleteness(isTaskComplete)
                scope.launch(Dispatchers.Main) {
                    //check if data is empty
                    if (!todos.isNullOrEmpty()) {
                        //data is not empty
                        view.onDataSuccess(todos)
                        view.setEmptyStateVisibility(false)
                    } else {
                        //data empty
                        view.onDataEmpty()
                        view.setEmptyStateVisibility(true)
                    }
                    view.setLoadingStatus(false)
                }
            } catch (e: Exception) {
                scope.launch(Dispatchers.Main) {
                    //when getting data is error
                    view.onDataFailed(e.message)
                    view.setLoadingStatus(false)
                    view.setEmptyStateVisibility(false)
                }
            }
        }
    }

    override fun deleteTodo(todo: Todo) {
        view.setLoadingStatus(true)
        scope.launch {
            try {
                val result = dataSource.deleteTodo(todo)
                scope.launch(Dispatchers.Main) {
                    //check if delete success
                    if (result.equals(1)) {
                        //delete success
                        view.onDeleteDataSuccess()
                    } else {
                        //delete failed
                        view.onDeleteDataFailed()
                    }
                    view.setLoadingStatus(false)
                }
            } catch (e: Exception) {
                scope.launch(Dispatchers.Main) {
                    //when getting data is error
                    view.onDeleteDataFailed()
                    view.setLoadingStatus(false)
                }
            }
        }
    }

}