package com.catnip.todolistapp.ui.tasklist

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
class TodoListViewModel(private val repository: TodoListRepository) : ViewModel(),
    TodoListContract.ViewModel {

    val todoData = MutableLiveData<Resource<List<Todo>>>()
    val deleteResponse = MutableLiveData<Boolean>()

    override fun getTodoByCompleteness(isTaskComplete: Boolean) {
        todoData.value = Resource.Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val todos = repository.getTodoByCompleteness(isTaskComplete)
                viewModelScope.launch(Dispatchers.Main) {
                    todoData.value = Resource.Success(todos)
                }
            } catch (e: Exception) {
                viewModelScope.launch(Dispatchers.Main) {
                    //when getting data is error
                    todoData.value = Resource.Error(e.message.orEmpty())
                }
            }
        }
    }

    override fun deleteTodo(todo: Todo) {
        viewModelScope.launch {
            try {
                val result = repository.deleteTodo(todo)
                viewModelScope.launch(Dispatchers.Main) {
                    //return response delete
                    deleteResponse.value = result == 1
                }
            } catch (e: Exception) {
                viewModelScope.launch(Dispatchers.Main) {
                    //when getting data is error
                    deleteResponse.value = false
                }
            }
        }
    }

}