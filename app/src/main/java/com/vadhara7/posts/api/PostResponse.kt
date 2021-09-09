package com.vadhara7.posts.api

import com.google.gson.annotations.SerializedName
import com.vadhara7.posts.model.Data
import com.vadhara7.posts.model.Meta


data class PostResponse(
    @SerializedName("code") val code: Int = 0,
    @SerializedName("data") val data: List<Data> = listOf(),
    @SerializedName("meta") val meta: Meta
)
