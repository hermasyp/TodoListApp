package com.catnip.todolistapp.ui.tasklist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.catnip.todolistapp.App
import com.catnip.todolistapp.data.constant.Constant
import com.catnip.todolistapp.data.datasource.TaskDataSource
import com.catnip.todolistapp.databinding.FragmentTaskListBinding
import com.catnip.todolistapp.ui.detail.DetailTaskActivity
import com.catnip.todolistapp.ui.tasklist.adapter.TaskAdapter

// TODO: Rename parameter arguments, choose names that match

/**
 * A simple [Fragment] subclass.
 * Use the [TaskListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TaskListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var isFilteredByTaskStatus: Boolean = false
    private lateinit var binding: FragmentTaskListBinding
    private lateinit var adapter: TaskAdapter


    companion object {
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private const val ARG_FILTERED_TASK = "ARG_FILTERED_TASK"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TaskListFragment.
         */
        // TODO: Rename and change types and number of parameters
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
        initSwipeRefresh()
    }

    override fun onResume() {
        super.onResume()
        initList()
    }

    private fun initList() {
        adapter = TaskAdapter { item, position ->
            val intent = Intent(context, DetailTaskActivity::class.java)
            intent.putExtra(Constant.EXTRAS_DATA_TODO, item)
            startActivity(intent)
        }
        binding.rvTask.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@TaskListFragment.adapter
        }
        (activity?.application as App).getDataSource()?.getTaskByStatus(isFilteredByTaskStatus)?.let {
            adapter.items = it
        }
    }

    private fun initSwipeRefresh(){
        binding.srlTask.setOnRefreshListener {
            binding.srlTask.isRefreshing = false
            (activity?.application as App).getDataSource()?.getTaskByStatus(isFilteredByTaskStatus)?.let {
                adapter.items = it
            }
        }
    }


}