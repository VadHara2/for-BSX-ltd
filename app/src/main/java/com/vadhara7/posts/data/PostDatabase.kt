package com.vadhara7.posts.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vadhara7.posts.data.responses.PostsList

@Database(entities = [PostsList::class], version = 1)
abstract class PostDatabase : RoomDatabase() {

    abstract fun restaurantDao(): PostDao
}