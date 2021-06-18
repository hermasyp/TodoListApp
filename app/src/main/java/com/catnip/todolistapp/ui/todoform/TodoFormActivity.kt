package com.catnip.todolistapp.ui.todoform

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.catnip.todolistapp.App
import com.catnip.todolistapp.R
import com.catnip.todolistapp.data.model.Todo
import com.catnip.todolistapp.databinding.ActivityTodoFormBinding
import kotlin.random.Random


class TodoFormActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTodoFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTodoFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setClick()
        supportActionBar?.title = getString(R.string.text_title_todo_form_activity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setClick() {
        binding.btnSaveTask.setOnClickListener {
            saveTodo()
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun saveTodo() {
        if (isFormTodoFilled()) {
            val todo = Todo(
                Random.nextInt(),
                binding.etTaskName.text.toString(),
                binding.etTaskDesc.text.toString(),
                binding.etTaskHeaderImg.text.toString(),
                false
            )
            (application as App).getDataSource()?.addTodo(todo)
            Toast.makeText(this, "Todo Saved!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun isFormTodoFilled(): Boolean {
        val title = binding.etTaskName.text.toString()
        val desc = binding.etTaskDesc.text.toString()
        val imgUrl = binding.etTaskHeaderImg.text.toString()
        var isFormValid = true

        //for checking is title empty
        if (title.isEmpty()) {
            isFormValid = false
            binding.tilTaskName.isErrorEnabled = true
            binding.tilTaskName.error = getString(R.string.error_form_task_title_empty)
        } else {
            binding.tilTaskName.isErrorEnabled = false
        }

        //for checking is desc empty
        if (desc.isEmpty()) {
            isFormValid = false
            binding.tilTaskDesc.isErrorEnabled = true
            binding.tilTaskDesc.error = getString(R.string.error_form_task_desc_empty)
        } else {
            binding.tilTaskDesc.isErrorEnabled = false
        }

        //for checking is img url empty
        if (imgUrl.isEmpty()) {
            isFormValid = false
            binding.tilTaskHeaderImg.isErrorEnabled = true
            binding.tilTaskHeaderImg.error = getString(R.string.error_form_task_img_url_empty)
        } else {
            binding.tilTaskHeaderImg.isErrorEnabled = false
        }
        return isFormValid
    }


}