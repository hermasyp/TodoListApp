package com.catnip.todolistapp.ui.detail

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.catnip.todolistapp.App
import com.catnip.todolistapp.R
import com.catnip.todolistapp.data.constant.Constant
import com.catnip.todolistapp.data.local.room.TodoRoomDatabase
import com.catnip.todolistapp.data.local.room.datasource.TodoDataSource
import com.catnip.todolistapp.data.model.Todo
import com.catnip.todolistapp.databinding.ActivityDetailTaskBinding
import com.catnip.todolistapp.ui.todoform.TodoFormActivity
import com.catnip.todolistapp.utils.ShareUtils
import com.google.android.material.snackbar.Snackbar


class DetailTodoActivity : AppCompatActivity(),DetailTodoContract.View {
    private lateinit var binding: ActivityDetailTaskBinding
    private lateinit var presenter: DetailTodoContract.Presenter
    private var todoId : Int? = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun getIntentData() {
        todoId = intent?.getIntExtra(Constant.EXTRAS_DATA_TODO,-1)
    }

    private fun bindData(todo : Todo?) {
        supportActionBar?.hide()
        binding.tvDescTask.text = todo?.desc
        binding.tvTitleTask.text = todo?.title
        Glide.with(this)
            .load(todo?.imgHeaderUrl)
            .centerCrop()
            .placeholder(R.drawable.ic_placeholder)
            .into(binding.ivHeaderTask)
        binding.ivShare.setOnClickListener {
            ShareUtils.shareText(this, "Title Task : ${todo?.title}\nDesc Task :  ${todo?.desc}")
        }
        setFabIcon(todo)
        binding.fab.setOnClickListener {
            todo?.let {
                presenter.changeStatusTodo(it)
            }
        }
        binding.ivEditTodo.setOnClickListener {
            TodoFormActivity.startActivity(this,TodoFormActivity.MODE_EDIT,todo)
        }
    }

    private fun setFabIcon(todo : Todo?) {
        binding.fab.setImageResource(if (todo?.isTaskCompleted == true) R.drawable.ic_task_done_true else R.drawable.ic_task_done_false)
    }

    override fun onFetchDetailSuccess(todo: Todo) {
        bindTodoData(todo)
    }

    override fun onFetchDetailFailed() {
        Toast.makeText(this, "Get Data Failed for id $todoId", Toast.LENGTH_SHORT).show()
    }

    override fun onChangeTodoStatusSuccess(todo: Todo) {
        bindTodoData(todo)
        if (todo.isTaskCompleted) {
            Snackbar.make(binding.root, "Success Set Todo to Done", Snackbar.LENGTH_SHORT)
                .show()
        } else {
            Snackbar.make(binding.root, "Success Set Todo to Undone", Snackbar.LENGTH_SHORT)
                .show()
        }
    }

    override fun onChangeTodoStatusFailed() {
        Toast.makeText(this, "Change todo status Failed", Toast.LENGTH_SHORT).show()
    }

    override fun bindTodoData(todo: Todo?) {
        bindData(todo)
    }

    override fun getData() {
        todoId?.let { presenter.getDetailTodo(it) }
    }

    override fun initView() {
        binding = ActivityDetailTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        getIntentData()
        //initialize presenter
        val dataSource = TodoDataSource(TodoRoomDatabase.getInstance(this).todoDao())
        presenter = DetailTodoPresenter(dataSource,this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        getData()
    }

}