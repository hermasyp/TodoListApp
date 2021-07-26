package com.catnip.todolistapp.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.catnip.todolistapp.base.Resource
import com.catnip.todolistapp.data.model.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class DetailTodoViewModel(private val repository: DetailTodoRepository) : ViewModel(),
    DetailTodoContract.ViewModel {

    val detailTodoData = MutableLiveData<Resource<Todo>>()
    val changeStatusTodoResult = MutableLiveData<Pair<Boolean, Todo?>>()

    override fun getDetailTodo(todoId: Int) {
        detailTodoData.value = Resource.Loading()
        viewModelScope.launch {
            try {
                val todo = repository.getTodoById(todoId)
                viewModelScope.launch(Dispatchers.Main) {
                    detailTodoData.value = Resource.Success(todo)
                }

            } catch (e: Exception) {
                viewModelScope.launch(Dispatchers.Main) {
                    detailTodoData.value = Resource.Error(e.message.orEmpty())
                }
            }
        }
    }

    override fun changeStatusTodo(todo: Todo) {
        viewModelScope.launch {
            try {
                val todo = repository.changeTodoStatus(todo)
                viewModelScope.launch(Dispatchers.Main) {
                    changeStatusTodoResult.value = Pair(true, todo)
                }

            } catch (e: Exception) {
                viewModelScope.launch(Dispatchers.Main) {
                    changeStatusTodoResult.value = Pair(false, null)
                }
            }
        }
    }

}