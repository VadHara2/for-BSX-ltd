package com.vadhara7.posts.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vadhara7.posts.model.Data


@Database(entities = [Data::class, RemoteKeys::class], version = 1, exportSchema = false)
abstract class PostDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
    abstract fun remoteKeysDao(): RemoteKeysDao
}