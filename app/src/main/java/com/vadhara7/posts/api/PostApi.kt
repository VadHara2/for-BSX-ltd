package com.vadhara7.posts.api


import com.vadhara7.posts.data.responses.PostsList
import retrofit2.http.GET
import retrofit2.http.Query

interface PostApi {
    companion object {
        const val BASE_URL = "https://gorest.co.in/public-api/"
    }

    @GET("posts")
    suspend fun getPosts(@Query("page") page: Int = 1): PostsList
}