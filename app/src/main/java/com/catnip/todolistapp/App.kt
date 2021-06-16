package com.catnip.todolistapp

import android.app.Application
import com.catnip.todolistapp.data.datasource.TaskDataSource

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class App : Application() {
    private var dataSource : TaskDataSource? = null

    override fun onCreate() {
        super.onCreate()
        dataSource = TaskDataSource()
    }

    fun getDataSource() : TaskDataSource? = dataSource


}