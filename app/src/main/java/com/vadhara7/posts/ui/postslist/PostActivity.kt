package com.vadhara7.posts.ui.postslist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.vadhara7.posts.R
import com.vadhara7.posts.databinding.ActivityPostBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

private const val TAG = "PostActivity"

@AndroidEntryPoint
class PostActivity : AppCompatActivity(R.layout.activity_post) {
    private val viewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Posts)
        super.onCreate(savedInstanceState)
        val binding = ActivityPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val postAdapter = PostAdapter()
        val header = ReposLoadStateAdapter { postAdapter.retry() }

        binding.apply {

            recyclerView.apply {
                adapter = postAdapter.withLoadStateHeaderAndFooter(
                    header = header,
                    footer = ReposLoadStateAdapter { postAdapter.retry() }
                )
                layoutManager = LinearLayoutManager(this@PostActivity)
            }

        }

        lifecycleScope.launch {

            viewModel.posts.collectLatest {
                postAdapter.submitData(it)
            }

        }

        lifecycleScope.launch {
            postAdapter.loadStateFlow.collect { loadState ->
                binding.progressBar.isVisible = loadState.mediator?.refresh is LoadState.Loading

                val errorState = loadState.source.append as? LoadState.Error
                    ?: loadState.source.prepend as? LoadState.Error
                    ?: loadState.append as? LoadState.Error
                    ?: loadState.prepend as? LoadState.Error
                errorState?.let {
                    Toast.makeText(
                        this@PostActivity,
                        "\uD83D\uDE28 Wooops ${it.error}",
                        Toast.LENGTH_LONG
                    ).show()
                }

            }
        }


    }
}