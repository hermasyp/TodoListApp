package com.catnip.todolistapp.ui.tasklist

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.catnip.todolistapp.R
import com.catnip.todolistapp.data.constant.Constant
import com.catnip.todolistapp.data.local.room.TodoRoomDatabase
import com.catnip.todolistapp.data.local.room.datasource.TodoDataSource
import com.catnip.todolistapp.data.model.Todo
import com.catnip.todolistapp.databinding.FragmentTaskListBinding
import com.catnip.todolistapp.ui.detail.DetailTodoActivity
import com.catnip.todolistapp.ui.tasklist.adapter.TodoAdapter


class TodoListFragment : Fragment(), TodoListContract.View {
    // TODO: Rename and change types of parameters
    private var isFilteredByTaskStatus: Boolean = false
    private lateinit var binding: FragmentTaskListBinding
    private lateinit var adapter: TodoAdapter
    private lateinit var presenter: TodoListContract.Presenter


    companion object {
        private const val ARG_FILTERED_TASK = "ARG_FILTERED_TASK"

        @JvmStatic
        fun newInstance(isFilterTaskByDone: Boolean) =
            TodoListFragment().apply {
                arguments = Bundle().apply {
                    putBoolean(ARG_FILTERED_TASK, isFilterTaskByDone)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            isFilteredByTaskStatus = it.getBoolean(ARG_FILTERED_TASK)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTaskListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onResume() {
        super.onResume()
        getData()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun getData() {
        presenter.getTodoByCompleteness(isFilteredByTaskStatus)
    }

    override fun onDataSuccess(todos: List<Todo>) {
        todos.let {
            adapter.items = it
        }
    }

    override fun onDataFailed(msg: String?) {
        Toast.makeText(context, msg ?: "Get Data Failed", Toast.LENGTH_SHORT).show()
    }

    override fun onDataEmpty() {
        adapter.items = mutableListOf()
    }

    override fun onDeleteDataSuccess() {
        getData()
    }

    override fun onDeleteDataFailed() {
        Toast.makeText(context, "Delete Data Failed", Toast.LENGTH_SHORT).show()
    }

    override fun setLoadingStatus(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun setEmptyStateVisibility(isDataEmpty: Boolean) {
        binding.tvMessage.text = getString(R.string.text_empty_data)
        binding.tvMessage.visibility = if (isDataEmpty) View.VISIBLE else View.GONE
    }

    override fun initList() {
        adapter = TodoAdapter({ todo, pos ->
            //event on click
            val intent = Intent(context, DetailTodoActivity::class.java)
            intent.putExtra(Constant.EXTRAS_DATA_TODO, todo.id)
            startActivity(intent)
        }, { todo, pos ->
            //event on long click
            showDialogDeleteTodo(todo)
        })
        binding.rvTask.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@TodoListFragment.adapter
        }
    }

    override fun initView() {
        context?.let {
            val dataSource = TodoDataSource(TodoRoomDatabase.getInstance(it).todoDao())
            presenter = TodoListPresenter(dataSource, this@TodoListFragment)
        }
        initSwipeRefresh()
        initList()
    }


    private fun initSwipeRefresh() {
        binding.srlTask.setOnRefreshListener {
            binding.srlTask.isRefreshing = false
            getData()
        }
    }

    private fun showDialogDeleteTodo(todo: Todo) {
        val alertDialog: AlertDialog? = activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setTitle("Do you want to delete \"${todo.title}\" ?")
                setPositiveButton(R.string.text_dialog_delete_task_positive) { dialog, id ->
                    presenter.deleteTodo(todo)
                    dialog?.dismiss()
                }
                setNegativeButton(R.string.text_dialog_delete_task_negative) { dialog, id ->
                    // User cancelled the dialog
                    dialog?.dismiss()
                }
            }
            builder.create()
        }
        alertDialog?.show()
    }


}