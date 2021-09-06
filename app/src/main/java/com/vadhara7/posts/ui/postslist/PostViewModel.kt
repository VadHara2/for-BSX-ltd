package com.vadhara7.posts.ui.postslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.vadhara7.posts.data.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(repository: PostRepository) : ViewModel() {
    val posts = repository.getRestaurants().asLiveData()
}