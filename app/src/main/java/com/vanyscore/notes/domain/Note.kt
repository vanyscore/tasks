package com.vanyscore.notes.domain

import android.net.Uri
import java.util.Calendar
import java.util.Date

data class Note(
    val id: Int? = null,
    val title: String = "",
    val description: String = "",
    val created: Date = Calendar.getInstance().time,
    val edited: Date = Calendar.getInstance().time,
    val images: List<Uri> = listOf(
        Uri.parse("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSpmYrXnE51Hn7cTHaoJfbIZwZMF8chYRnB6A&s"),
        Uri.parse("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSpmYrXnE51Hn7cTHaoJfbIZwZMF8chYRnB6A&s"),
        Uri.parse("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSpmYrXnE51Hn7cTHaoJfbIZwZMF8chYRnB6A&s"),
        Uri.parse("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSpmYrXnE51Hn7cTHaoJfbIZwZMF8chYRnB6A&s"),
        Uri.parse("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSpmYrXnE51Hn7cTHaoJfbIZwZMF8chYRnB6A&s"),
        Uri.parse("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSpmYrXnE51Hn7cTHaoJfbIZwZMF8chYRnB6A&s"),
        Uri.parse("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSpmYrXnE51Hn7cTHaoJfbIZwZMF8chYRnB6A&s"),
        Uri.parse("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSpmYrXnE51Hn7cTHaoJfbIZwZMF8chYRnB6A&s"),
    ),
)