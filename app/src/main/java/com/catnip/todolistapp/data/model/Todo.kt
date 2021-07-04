package com.catnip.todolistapp.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
@Parcelize
@Entity(tableName = "todo")
data class Todo (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "title")
    var title : String?,
    @ColumnInfo(name = "desc")
    var desc : String?,
    @ColumnInfo(name = "img_header_url")
    var imgHeaderUrl: String?,
    @ColumnInfo(name = "is_task_complete")
    var isTaskCompleted: Boolean = false
) : Parcelable
