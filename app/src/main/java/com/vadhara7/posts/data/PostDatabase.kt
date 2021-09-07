package com.vadhara7.posts.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vadhara7.posts.data.responses.Data


@Database(entities = [Data::class], version = 5)
abstract class PostDatabase : RoomDatabase() {
    abstract fun restaurantDao(): PostDao
}