package com.catnip.todolistapp.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.catnip.todolistapp.App
import com.catnip.todolistapp.R
import com.catnip.todolistapp.data.constant.Constant
import com.catnip.todolistapp.data.model.Todo
import com.catnip.todolistapp.databinding.ActivityDetailTaskBinding
import com.catnip.todolistapp.utils.ShareUtils
import com.google.android.material.snackbar.Snackbar





class DetailTaskActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailTaskBinding
    private var todo: Todo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getIntentData()
        bindData()
    }

    private fun getIntentData() {
        todo = intent?.getParcelableExtra(Constant.EXTRAS_DATA_TODO)
    }

    private fun bindData() {
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
        setFabIcon()
        binding.fab.setOnClickListener {
            todo?.let {
                (application as App).getDataSource()?.changeStatusTodo(it.id)
                todo = todo?.apply { isTaskCompleted = isTaskCompleted.not() }
                setFabIcon()
                if(todo?.isTaskCompleted == true){
                    Snackbar.make(binding.root, "Success Set Todo to Done", Snackbar.LENGTH_SHORT).show()
                }else{
                    Snackbar.make(binding.root, "Success Set Todo to Undone", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setFabIcon() {
        binding.fab.setImageResource(if (todo?.isTaskCompleted == true) R.drawable.ic_task_done_true else R.drawable.ic_task_done_false)
    }
}