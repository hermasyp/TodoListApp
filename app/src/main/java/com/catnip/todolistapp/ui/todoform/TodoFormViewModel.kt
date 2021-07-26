package com.catnip.todolistapp.ui.todoform

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.catnip.todolistapp.data.model.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class TodoFormViewModel(private val repository: TodoFormRepository) : ViewModel(),
    TodoFormContract.ViewModel {

    val transactionResult = MutableLiveData<Boolean>()

    override fun insertTodo(todo: Todo) {
        viewModelScope.launch {
            try {
                val todoId = repository.addTodo(todo)
                viewModelScope.launch (Dispatchers.Main){
                    transactionResult.value = todoId > 0
                }
            } catch (e: Exception) {
                viewModelScope.launch (Dispatchers.Main){
                    transactionResult.value = false
                }
            }
        }
    }

    override fun updateTodo(todo: Todo) {
        viewModelScope.launch {
            try {
                val todoId = repository.updateTodo(todo)
                viewModelScope.launch (Dispatchers.Main){
                    transactionResult.value = todoId > 0
                }
            } catch (e: Exception) {
                viewModelScope.launch (Dispatchers.Main){
                    transactionResult.value = false
                }
            }
        }
    }

}