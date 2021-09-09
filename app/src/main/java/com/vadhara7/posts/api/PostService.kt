package com.vadhara7.posts.api


import retrofit2.http.GET
import retrofit2.http.Query

interface PostService {
    companion object {
        const val BASE_URL = "https://gorest.co.in/public-api/"
    }

    @GET("posts")
    suspend fun getPosts(@Query("page") page: Int = 1): PostResponse
}