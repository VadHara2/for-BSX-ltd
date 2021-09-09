package com.vadhara7.posts.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.vadhara7.posts.api.PostService
import com.vadhara7.posts.model.Data
import com.vadhara7.posts.db.PostDatabase
import com.vadhara7.posts.db.RemoteKeys
import retrofit2.HttpException
import java.io.IOException


private const val GITHUB_STARTING_PAGE_INDEX = 1

@OptIn(ExperimentalPagingApi::class)
class PostRemoteMediator(
    private val service: PostService,
    private val postDatabase: PostDatabase
) : RemoteMediator<Int, Data>() {

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Data>): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: GITHUB_STARTING_PAGE_INDEX
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevKey = remoteKeys?.prevKey
                if (prevKey == null) {
                    return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                }
                prevKey
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey
                if (nextKey == null) {
                    return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                }
                nextKey
            }
        }

        try {
            val apiResponse = service.getPosts(page)

            val posts = apiResponse.data
            val endOfPaginationReached = posts.isEmpty()
            postDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    postDatabase.remoteKeysDao().clearRemoteKeys()
                    postDatabase.postDao().deleteAllPosts()
                }
                val prevKey = if (page == GITHUB_STARTING_PAGE_INDEX) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1
                val keys = posts.map {
                    RemoteKeys(pageId = it.id, prevKey = prevKey, nextKey = nextKey)
                }
                postDatabase.remoteKeysDao().insertAll(keys)
                postDatabase.postDao().insertPosts(posts)
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, Data>): RemoteKeys? {
        return state.pages.lastOrNull() { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { repo ->
                postDatabase.remoteKeysDao().remoteKeysRepoId(repo.id)
            }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, Data>): RemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { repo ->
                postDatabase.remoteKeysDao().remoteKeysRepoId(repo.id)
            }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Data>
    ): RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { repoId ->
                postDatabase.remoteKeysDao().remoteKeysRepoId(repoId)
            }
        }
    }

}