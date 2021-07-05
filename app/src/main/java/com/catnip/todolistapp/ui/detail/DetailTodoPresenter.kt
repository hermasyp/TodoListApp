package com.catnip.todolistapp.ui.detail

import com.catnip.todolistapp.base.BasePresenter
import com.catnip.todolistapp.data.local.room.datasource.TodoDataSource
import com.catnip.todolistapp.data.model.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class DetailTodoPresenter(
    private val dataSource: TodoDataSource,
    private val view: DetailTodoContract.View
) : BasePresenter(), DetailTodoContract.Presenter {
    override fun getDetailTodo(todoId: Int) {
        scope.launch {
            try {
                val todo = dataSource.getTodoById(todoId)
                scope.launch (Dispatchers.Main){
                    view.onFetchDetailSuccess(todo)
                }

            } catch (e: Exception) {
                scope.launch (Dispatchers.Main){
                    view.onFetchDetailFailed()
                }
            }
        }
    }

    override fun changeStatusTodo(todo: Todo) {
        scope.launch {
            try {
                val todoResult = dataSource.changeTodoStatus(todo)
                scope.launch (Dispatchers.Main){
                    view.onChangeTodoStatusSuccess(todoResult)
                }
            } catch (e: Exception) {
                scope.launch (Dispatchers.Main){
                    view.onChangeTodoStatusFailed()
                }
            }
        }
    }

}