package com.catnip.todolistapp.ui.todoform

import com.catnip.todolistapp.base.BaseContract
import com.catnip.todolistapp.data.model.Todo

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface TodoFormContract {
    interface Presenter : BaseContract.BasePresenter {
        fun insertTodo(todo: Todo)
        fun updateTodo(todo: Todo)
    }

    interface View : BaseContract.BaseView {
        fun onSuccess()
        fun onFailed()
        fun getIntentData()
        fun initializeForm()
    }
}