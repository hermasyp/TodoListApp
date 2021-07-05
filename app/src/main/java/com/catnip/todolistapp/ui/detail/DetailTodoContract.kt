package com.catnip.todolistapp.ui.detail

import com.catnip.todolistapp.base.BaseContract
import com.catnip.todolistapp.data.model.Todo

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface DetailTodoContract {
    interface Presenter : BaseContract.BasePresenter {
        fun getDetailTodo(todoId: Int)
        fun changeStatusTodo(todo: Todo)
    }

    interface View : BaseContract.BaseView {
        fun onFetchDetailSuccess(todo: Todo)
        fun onFetchDetailFailed()

        fun onChangeTodoStatusSuccess(todo : Todo)
        fun onChangeTodoStatusFailed()

        fun bindTodoData(todo : Todo?)

    }
}