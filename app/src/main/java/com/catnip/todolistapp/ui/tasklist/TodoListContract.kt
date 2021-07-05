package com.catnip.todolistapp.ui.tasklist

import com.catnip.todolistapp.base.BaseContract
import com.catnip.todolistapp.data.model.Todo

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface TodoListContract {
    interface Presenter : BaseContract.BasePresenter {
        fun getTodosByCompleteness(isTaskComplete: Boolean)
        fun deleteTodo(todo: Todo)
    }

    interface View : BaseContract.BaseView {
        fun getData()

        fun onDataSuccess(todo: List<Todo>)
        fun onDataEmpty()
        fun onDataFailed(msg: String?)

        fun onDeleteDataSuccess()
        fun onDeleteDataFailed()

        fun setLoadingStatus(isLoading : Boolean)
        fun setEmptyState(isDataEmpty : Boolean)

        fun initList()
    }
}