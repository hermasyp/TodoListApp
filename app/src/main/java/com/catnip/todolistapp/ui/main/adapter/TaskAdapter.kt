package com.catnip.todolistapp.ui.main.adapter;

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.catnip.todolistapp.data.model.Todo
import com.catnip.todolistapp.databinding.ItemTaskBinding

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class TaskAdapter(val itemClick: (Todo) -> Unit) :
    RecyclerView.Adapter<TaskAdapter.TodoViewHolder>() {

    var items : List<Todo> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(binding, itemClick)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bindView(items[position])
    }

    override fun getItemCount(): Int = items.size


    class TodoViewHolder(private val binding: ItemTaskBinding, val itemClick: (Todo) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindView(item: Todo) {
            with(item) {
                binding.tvTitleItemTask.text = item.title
                itemView.setOnClickListener { itemClick(this) }
            }

        }
    }

}