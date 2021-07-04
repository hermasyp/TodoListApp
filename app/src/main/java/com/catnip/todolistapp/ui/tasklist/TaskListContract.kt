package com.catnip.todolistapp.ui.tasklist

import com.catnip.todolistapp.base.BaseContract
import com.catnip.todolistapp.data.model.Todo

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface TaskListContract {
    interface Presenter : BaseContract.BasePresenter {
        fun getTodosByCompleteness(isTaskComplete: Boolean)
        fun deleteTodo(todo: Todo)
    }

    interface View : BaseContract.BaseView {
        fun onDataSuccess(todo: List<Todo>)
        fun onDataEmpty()
        fun onDataFailed(msg: String?)

        fun onDeleteDataSuccess()
        fun onDeleteDataFailed()

        fun initList()
    }
}