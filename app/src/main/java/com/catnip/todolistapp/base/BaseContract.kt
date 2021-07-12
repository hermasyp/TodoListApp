package com.catnip.todolistapp.base

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface BaseContract {
    interface BasePresenter{
        fun onDestroy()
    }
    interface BaseView{
        fun initView()
    }
}