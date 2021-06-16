package com.catnip.todolistapp.data.datasource

import com.catnip.todolistapp.data.model.Todo

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class TaskDataSource {
    private var todos = mutableListOf<Todo>().apply {
        add(
            Todo(
                "Mencuci baju",
                "Harus mencuci baju orang serumah",
                "https://image-cdn.medkomtek.com/JWQlRCsCKHDuTFy4VN-CTsnMTBQ=/1200x675/smart/klikdokter-media-buckets/medias/2263302/original/061757600_1530255518-Agar-Tidak-Kram-Otot-Hindari-Ini-Saat-Mencuci-Baju-By-birdbyb-stockphoto-shutterstock.jpg",
                true
            )
        )
        add(
            Todo(
                "Working Project : Binar",
                "Membuat Project Challenge Binar CH 5",
                "https://media.istockphoto.com/vectors/origamisign2orange-vector-id1165147642?b=1&k=6&m=1165147642&s=612x612&w=0&h=gRulyoRq8aKs8GtetjLJHMyJ_4btD-V5zotQ1_ivvHE=",
                true
            )
        )
        add(
            Todo(
                "Working Project : Binar 2",
                "Membuat Project Challenge Binar CH 5 - 1",
                "https://media.istockphoto.com/vectors/origamisign2orange-vector-id1165147642?b=1&k=6&m=1165147642&s=612x612&w=0&h=gRulyoRq8aKs8GtetjLJHMyJ_4btD-V5zotQ1_ivvHE=",
                false
            )
        )
        add(
            Todo(
                "Working Project : Binar 3",
                "Membuat Project Challenge Binar CH 5 - 2",
                "https://media.istockphoto.com/vectors/origamisign2orange-vector-id1165147642?b=1&k=6&m=1165147642&s=612x612&w=0&h=gRulyoRq8aKs8GtetjLJHMyJ_4btD-V5zotQ1_ivvHE=",
                false
            )
        )
        add(
            Todo(
                "Working Project : Binar 4",
                "Membuat Project Challenge Binar CH 5 - 3",
                "https://media.istockphoto.com/vectors/origamisign2orange-vector-id1165147642?b=1&k=6&m=1165147642&s=612x612&w=0&h=gRulyoRq8aKs8GtetjLJHMyJ_4btD-V5zotQ1_ivvHE=",
                false
            )
        )
    }

    fun getTaskByStatus(isTaskCompleted: Boolean): List<Todo> {
        return todos.filter { it.isTaskCompleted == isTaskCompleted }
    }
}