package com.vadhara7.posts.data.responses

data class Pagination(
    val limit: Int,
    val page: Int,
    val pages: Int,
    val total: Int
)