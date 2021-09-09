package com.vadhara7.posts.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class Data(
    val title: String,
    val body: String,
    @PrimaryKey
    val id: Int,
    val user_id: Int
)