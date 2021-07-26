package com.catnip.todolistapp.ui.tasklist

import com.catnip.todolistapp.base.BaseContract
import com.catnip.todolistapp.data.model.Todo

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface TodoListContract {
    interface View : BaseContract.BaseView{
        //getting data from presenter
        fun getData()

        //callback get data
        fun onDataSuccess(todos : List<Todo>)
        fun onDataFailed(msg : String?)
        fun onDataEmpty()

        //callback when delete data
        fun onDeleteDataSuccess()
        fun onDeleteDataFailed()

        // set loading state and message visibility
        fun setLoadingStatus(isLoading : Boolean)
        fun setEmptyStateVisibility(isDataEmpty : Boolean)

        //initialize list
        fun initList()
    }
    interface ViewModel{
        fun getTodoByCompleteness(isTaskComplete : Boolean)
        fun deleteTodo(todo: Todo)
    }
}