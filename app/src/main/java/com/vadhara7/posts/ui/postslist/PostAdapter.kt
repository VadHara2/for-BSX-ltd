package com.vadhara7.posts.ui.postslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vadhara7.posts.data.responses.PostsList
import com.vadhara7.posts.databinding.PostItemBinding


class PostAdapter : ListAdapter<PostsList, PostAdapter.PostViewHolder>(PostComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding =
            PostItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    class PostViewHolder(private val binding: PostItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(post: PostsList) {
            binding.apply {
                titleText.text = post.code.toString()
                bodyText.text = post.code.toString()
            }
        }
    }

    class PostComparator : DiffUtil.ItemCallback<PostsList>() {
        override fun areItemsTheSame(oldItem: PostsList, newItem: PostsList) =
            oldItem.code == newItem.code

        override fun areContentsTheSame(oldItem: PostsList, newItem: PostsList) =
            oldItem == newItem
    }
}