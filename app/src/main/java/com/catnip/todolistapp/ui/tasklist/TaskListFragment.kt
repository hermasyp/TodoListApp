package com.catnip.todolistapp.ui.tasklist

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.catnip.todolistapp.R
import com.catnip.todolistapp.data.constant.Constant
import com.catnip.todolistapp.data.local.room.TodoRoomDatabase
import com.catnip.todolistapp.data.local.room.datasource.TaskDataSource
import com.catnip.todolistapp.data.model.Todo
import com.catnip.todolistapp.databinding.FragmentTaskListBinding
import com.catnip.todolistapp.ui.detail.DetailTaskActivity
import com.catnip.todolistapp.ui.tasklist.adapter.TaskAdapter

class TaskListFragment : Fragment(), TaskListContract.View {
    private val TAG = TaskListFragment::class.java.simpleName
    private var isFilteredByTaskStatus: Boolean = false
    private lateinit var binding: FragmentTaskListBinding
    private lateinit var adapter: TaskAdapter

    private lateinit var presenter: TaskListContract.Presenter

    companion object {
        private const val ARG_FILTERED_TASK = "ARG_FILTERED_TASK"

        @JvmStatic
        fun newInstance(isFilterTaskByDone: Boolean) =
            TaskListFragment().apply {
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
        presenter.getTodosByCompleteness(isFilteredByTaskStatus)
    }

    override fun initList() {
        adapter = TaskAdapter({ todo, pos ->
            val intent = Intent(context, DetailTaskActivity::class.java)
            intent.putExtra(Constant.EXTRAS_DATA_TODO, todo)
            startActivity(intent)
        }, { todo, pos ->
            showDialogDeleteTodo(todo)
        })
        binding.rvTask.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@TaskListFragment.adapter
        }
    }


    private fun initSwipeRefresh() {
        binding.srlTask.setOnRefreshListener {
            binding.srlTask.isRefreshing = false
            presenter.getTodosByCompleteness(isFilteredByTaskStatus)
        }
    }

    private fun showDialogDeleteTodo(todo: Todo) {
        val alertDialog: AlertDialog? = activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setTitle("Do you want to delete \"${todo.title}\" ?")
                setPositiveButton(R.string.text_dialog_delete_task_positive) { dialog, id ->
                    // User clicked OK button
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

    override fun onDataSuccess(todo: List<Todo>) {
        todo.let {
            adapter.items = it
        }
    }

    override fun onDataEmpty() {
        Log.d(TAG, "onDataEmpty: ")
    }

    override fun onDataFailed(msg: String?) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onDeleteDataSuccess() {
        presenter.getTodosByCompleteness(isFilteredByTaskStatus)
    }

    override fun onDeleteDataFailed() {
        Toast.makeText(context, "Delete Data Failed", Toast.LENGTH_SHORT).show()
    }

    override fun initView() {
        context?.let {
            val dataSource = TaskDataSource(TodoRoomDatabase.getInstance(it).todoDao())
            presenter = TaskListPresenter(dataSource, this)
        }
        initSwipeRefresh()
        initList()
    }


}