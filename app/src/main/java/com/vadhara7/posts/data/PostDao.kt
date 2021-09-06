package com.vadhara7.posts.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vadhara7.posts.data.responses.PostsList
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {
    @Query("SELECT * FROM posts")
    fun getAllPosts(): Flow<List<PostsList>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosts(restaurants: PostsList)

    @Query("DELETE FROM posts")
    suspend fun deleteAllPosts()
}