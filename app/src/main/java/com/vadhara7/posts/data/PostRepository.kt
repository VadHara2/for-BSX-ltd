package com.vadhara7.posts.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.vadhara7.posts.api.PostService
import com.vadhara7.posts.db.PostDatabase
import com.vadhara7.posts.model.Data
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val service: PostService,
    private val db: PostDatabase
) {
    private val postDao = db.postDao()

    fun getResultStream(): Flow<PagingData<Data>> {

        val pagingSourceFactory = { postDao.getAllPosts() }

        @OptIn(ExperimentalPagingApi::class)
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            remoteMediator = PostRemoteMediator(
                service,
                db
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 20
    }

}
