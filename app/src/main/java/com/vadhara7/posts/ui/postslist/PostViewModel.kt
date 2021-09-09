package com.vadhara7.posts.ui.postslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.vadhara7.posts.data.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(repository: PostRepository) : ViewModel() {
    val posts = repository.getResultStream().stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())
}