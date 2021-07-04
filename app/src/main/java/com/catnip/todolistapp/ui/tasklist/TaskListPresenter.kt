package com.catnip.todolistapp.ui.tasklist

import com.catnip.todolistapp.base.BasePresenter
import com.catnip.todolistapp.data.local.room.datasource.TaskDataSource
import com.catnip.todolistapp.data.model.Todo
import kotlinx.coroutines.launch

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class TaskListPresenter(
    private val dataSource: TaskDataSource,
    private val view: TaskListContract.View
) :
    BasePresenter(),
    TaskListContract.Presenter {

    override fun getTodosByCompleteness(isTaskComplete: Boolean) {
        scope.launch {
            try {
                val todos = dataSource.getTodoByCompleteness(isTaskComplete)
                if (!todos.isNullOrEmpty()) {
                    view.onDataSuccess(todos)
                } else {
                    view.onDataEmpty()
                }
            } catch (e: Exception) {
                view.onDataFailed(e.message)
            }
        }
    }

    override fun deleteTodo(todo: Todo) {
        scope.launch {
            try {
                val todos = dataSource.deleteTodo(todo)
                if (!todos.equals(1)) {
                    view.onDeleteDataSuccess()
                } else {
                    view.onDeleteDataFailed()
                }
            } catch (e: Exception) {
                view.onDeleteDataFailed()
            }
        }
    }


}