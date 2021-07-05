package com.catnip.todolistapp.ui.tasklist.adapter;

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.catnip.todolistapp.data.model.Todo
import com.catnip.todolistapp.databinding.ItemTaskBinding

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class TodoAdapter(val itemClick: (Todo, Int) -> Unit, val longClick: (Todo, Int) -> Unit) :
    RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    var items: List<Todo> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(binding, itemClick, longClick)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bindView(items[position], position)
    }

    override fun getItemCount(): Int = items.size



    class TodoViewHolder(
        private val binding: ItemTaskBinding,
        val itemClick: (Todo, Int) -> Unit,
        val longClick: (Todo, Int) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindView(item: Todo, position: Int) {
            with(item) {
                binding.tvTitleItemTask.text = item.title
                itemView.setOnClickListener { itemClick(this, position) }
                itemView.setOnLongClickListener {
                    longClick(this, position)
                    true
                }
            }

        }
    }

}