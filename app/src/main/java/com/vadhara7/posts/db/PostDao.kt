package com.vadhara7.posts.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vadhara7.posts.model.Data


@Dao
interface PostDao {
    @Query("SELECT * FROM posts")
    fun getAllPosts(): PagingSource<Int, Data>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosts(restaurants: List<Data>)

    @Query("DELETE FROM posts")
    suspend fun deleteAllPosts()
}