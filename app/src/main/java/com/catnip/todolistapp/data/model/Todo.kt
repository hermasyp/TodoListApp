package com.catnip.todolistapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
@Parcelize
data class Todo (
    val title : String?,
    val desc : String?,
    val imgHeaderUrl: String?
) : Parcelable
