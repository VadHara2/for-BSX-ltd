package com.vadhara7.posts.data.responses

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class PostsList(
    @PrimaryKey
    val code: Int,
//    val data: List<Data>,
//    val meta: Meta
)