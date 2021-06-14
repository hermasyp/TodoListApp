package com.catnip.todolistapp.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.catnip.todolistapp.R
import com.catnip.todolistapp.data.constant.Constant
import com.catnip.todolistapp.data.model.Todo
import com.catnip.todolistapp.databinding.ActivityDetailTaskBinding
import com.catnip.todolistapp.utils.ShareUtils

class DetailTaskActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailTaskBinding
    private var todo: Todo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getIntentData()
        bindData()
    }

    private fun getIntentData(){
        todo = intent?.getParcelableExtra(Constant.EXTRAS_DATA_TODO)
    }
    private fun bindData(){
        supportActionBar?.hide()
        binding.tvDescTask.text = todo?.desc
        binding.tvTitleTask.text = todo?.title
        Glide.with(this)
            .load(todo?.imgHeaderUrl)
            .centerCrop()
            .placeholder(R.drawable.ic_placeholder)
            .into(binding.ivHeaderTask)
        binding.ivShare.setOnClickListener {
            ShareUtils.shareText(this,"Title Task : ${todo?.title}\nDesc Task :  ${todo?.desc}")
        }
    }

}