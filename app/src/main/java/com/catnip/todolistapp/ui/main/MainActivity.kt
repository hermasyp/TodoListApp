package com.catnip.todolistapp.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.catnip.todolistapp.R
import com.catnip.todolistapp.data.constant.Constant
import com.catnip.todolistapp.data.model.Todo
import com.catnip.todolistapp.databinding.ActivityMainBinding
import com.catnip.todolistapp.ui.detail.DetailTaskActivity
import com.catnip.todolistapp.ui.main.adapter.TaskAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: TaskAdapter
    private val TAG = MainActivity::class.java.simpleName


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initList()
    }

    private fun initList(){
        adapter = TaskAdapter {
            val intent = Intent(this,DetailTaskActivity::class.java)
            intent.putExtra(Constant.EXTRAS_DATA_TODO,it)
            startActivity(intent)
        }
        binding.rvTask.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
        adapter.items = generateDummyData()
    }

    private fun generateDummyData() : List<Todo>{
        return mutableListOf<Todo>().apply {
            add(Todo("Mencuci baju", "Harus mencuci baju orang serumah","https://image-cdn.medkomtek.com/JWQlRCsCKHDuTFy4VN-CTsnMTBQ=/1200x675/smart/klikdokter-media-buckets/medias/2263302/original/061757600_1530255518-Agar-Tidak-Kram-Otot-Hindari-Ini-Saat-Mencuci-Baju-By-birdbyb-stockphoto-shutterstock.jpg"))
            add(Todo("Working Project : Binar", "Membuat Project Challenge Binar CH 5","https://media.istockphoto.com/vectors/origamisign2orange-vector-id1165147642?b=1&k=6&m=1165147642&s=612x612&w=0&h=gRulyoRq8aKs8GtetjLJHMyJ_4btD-V5zotQ1_ivvHE="))
            add(Todo("Working Project : Binar 2", "Membuat Project Challenge Binar CH 5 - 1","https://media.istockphoto.com/vectors/origamisign2orange-vector-id1165147642?b=1&k=6&m=1165147642&s=612x612&w=0&h=gRulyoRq8aKs8GtetjLJHMyJ_4btD-V5zotQ1_ivvHE="))
            add(Todo("Working Project : Binar 3", "Membuat Project Challenge Binar CH 5 - 2","https://media.istockphoto.com/vectors/origamisign2orange-vector-id1165147642?b=1&k=6&m=1165147642&s=612x612&w=0&h=gRulyoRq8aKs8GtetjLJHMyJ_4btD-V5zotQ1_ivvHE="))
            add(Todo("Working Project : Binar 4", "Membuat Project Challenge Binar CH 5 - 3","https://media.istockphoto.com/vectors/origamisign2orange-vector-id1165147642?b=1&k=6&m=1165147642&s=612x612&w=0&h=gRulyoRq8aKs8GtetjLJHMyJ_4btD-V5zotQ1_ivvHE="))
        }
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
    }
}