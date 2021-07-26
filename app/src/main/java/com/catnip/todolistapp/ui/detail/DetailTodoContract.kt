package com.catnip.todolistapp.ui.detail

import com.catnip.todolistapp.base.BaseContract
import com.catnip.todolistapp.data.model.Todo

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface DetailTodoContract {
    interface View : BaseContract.BaseView {
        //callback when get data detail
        fun onFetchDetailSuccess(todo: Todo)
        fun onFetchDetailFailed()

        //callback when change todo status
        fun onChangeTodoStatusSuccess(todo : Todo)
        fun onChangeTodoStatusFailed()

        //set data to layout
        fun bindTodoData(todo: Todo?)

        //getting data
        fun getData()
    }

    interface ViewModel {
        fun getDetailTodo(todoId: Int)
        fun changeStatusTodo(todo: Todo)
    }
}