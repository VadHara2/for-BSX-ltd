package com.vadhara7.posts.data

import androidx.room.withTransaction
import com.vadhara7.posts.api.PostApi
import com.vadhara7.posts.util.networkBoundResource
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val api: PostApi,
    private val db: PostDatabase
) {
    private val restaurantDao = db.restaurantDao()

    fun getRestaurants() = networkBoundResource(
        query = {
            restaurantDao.getAllPosts()
        },
        fetch = {
            api.getPosts()
        },
        saveFetchResult = { posts ->
            db.withTransaction {
                restaurantDao.deleteAllPosts()
                restaurantDao.insertPosts(posts.data)
            }
        }
    )
}