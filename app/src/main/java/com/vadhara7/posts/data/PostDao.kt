package com.vadhara7.posts.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vadhara7.posts.data.responses.Data
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {
    @Query("SELECT * FROM posts")
    fun getAllPosts(): Flow<List<Data>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosts(restaurants: List<Data>)

    @Query("DELETE FROM posts")
    suspend fun deleteAllPosts()
}