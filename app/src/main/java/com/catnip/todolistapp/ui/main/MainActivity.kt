package com.catnip.todolistapp.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.catnip.todolistapp.R
import com.catnip.todolistapp.databinding.ActivityMainBinding
import com.catnip.todolistapp.ui.about.AboutDialogFragment
import com.catnip.todolistapp.ui.tasklist.TaskListFragment
import com.catnip.todolistapp.ui.todoform.TodoFormActivity
import com.catnip.todolistapp.utils.views.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val TAG = MainActivity::class.java.simpleName


    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate: ")
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewPager()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main_activity, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_add_todo -> {
                navigateToTodoForm()
                true
            }
            R.id.menu_about -> {
                openDialogAbout()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initViewPager() {
        val fragmentAdapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        fragmentAdapter.addFragment(TaskListFragment.newInstance(false), "Undone Task")
        fragmentAdapter.addFragment(TaskListFragment.newInstance(true), "Done Task")
        binding.viewPager.apply {
            adapter = fragmentAdapter
        }
        TabLayoutMediator(binding.tabLayout, binding.viewPager, true) { tab, position ->
            tab.text = fragmentAdapter.getPageTitle(position)
        }.attach()
    }

    private fun navigateToTodoForm() {
        TodoFormActivity.startActivity(this, TodoFormActivity.MODE_INSERT)
    }

    private fun openDialogAbout() {
        AboutDialogFragment().show(supportFragmentManager, null)
    }

}