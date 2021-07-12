package com.catnip.todolistapp.ui.todoform

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.catnip.todolistapp.R
import com.catnip.todolistapp.data.local.room.TodoRoomDatabase
import com.catnip.todolistapp.data.local.room.datasource.TodoDataSource
import com.catnip.todolistapp.data.model.Todo
import com.catnip.todolistapp.databinding.ActivityTodoFormBinding

/*
* Function :
* 1. Input data
* 2. Edit data.
* */
class TodoFormActivity : AppCompatActivity(), TodoFormContract.View {
    private lateinit var binding: ActivityTodoFormBinding
    private lateinit var presenter: TodoFormContract.Presenter
    private var formMode: Int = MODE_INSERT
    private var todo: Todo? = null

    companion object {
        const val MODE_INSERT = 0
        const val MODE_EDIT = 1
        const val ARG_MODE = "ARG_MODE"
        const val ARG_TODO_DATA = "ARG_TODO_DATA"

        fun startActivity(context: Context, formMode: Int, todo: Todo?) {
            val intent = Intent(context, TodoFormActivity::class.java)
            intent.putExtra(ARG_MODE, formMode)
            todo?.let {
                intent.putExtra(ARG_TODO_DATA, todo)
            }
            context.startActivity(intent)
        }

        fun startActivity(context: Context, formMode: Int) {
            startActivity(context, formMode, null)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
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
            if (formMode == MODE_EDIT) {
                // do edit
                todo = todo?.copy()?.apply {
                    title = binding.etTaskName.text.toString()
                    desc = binding.etTaskDesc.text.toString()
                    imgHeaderUrl = binding.etTaskHeaderImg.text.toString()
                }
                todo?.let { presenter.updateTodo(it) }
            } else {
                //do insert
                todo = Todo(
                    title = binding.etTaskName.text.toString(),
                    desc = binding.etTaskDesc.text.toString(),
                    imgHeaderUrl = binding.etTaskHeaderImg.text.toString(),
                )
                todo?.let { presenter.insertTodo(it) }
            }
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

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun onSuccess() {
        //when save data success
        Toast.makeText(this, "Save todo Success!", Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun onFailed() {
        //when save data failed
        Toast.makeText(this, "Save todo Failed!", Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun getIntentData() {
        formMode = intent.getIntExtra(ARG_MODE, 0)
        todo = intent.getParcelableExtra(ARG_TODO_DATA)
    }

    override fun initializeForm() {
        //initialize presenter
        val dataSource = TodoDataSource(TodoRoomDatabase.getInstance(this).todoDao())
        presenter = TodoFormPresenter(dataSource, this)
        //preset data when form mode is edit mode
        if (formMode == MODE_EDIT) {
            todo?.let {
                binding.etTaskName.setText(it.title)
                binding.etTaskDesc.setText(it.desc)
                binding.etTaskHeaderImg.setText(it.imgHeaderUrl)
            }
            //"Edit Data"
            supportActionBar?.title = getString(R.string.text_title_edit_todo_form_activity)
        } else {
            supportActionBar?.title = getString(R.string.text_title_todo_form_activity)
        }
    }

    override fun initView() {
        binding = ActivityTodoFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setClick()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //get intent data
        getIntentData()
        //initialize form if edit or insert
        initializeForm()
    }


}