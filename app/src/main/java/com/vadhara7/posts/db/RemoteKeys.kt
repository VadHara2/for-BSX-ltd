package com.vadhara7.posts.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKeys(
    @PrimaryKey
    val pageId: Int,
    val prevKey: Int?,
    val nextKey: Int?
)