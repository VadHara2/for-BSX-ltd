package com.vadhara7.posts.ui.postslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.vadhara7.posts.model.Data
import com.vadhara7.posts.databinding.PostItemBinding


class PostAdapter : PagingDataAdapter<Data, PostAdapter.PostViewHolder>(PostComparator()) {

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

        fun bind(post: Data) {
            binding.apply {
                titleText.text = post.title
                bodyText.text = post.body
            }
        }
    }

    class PostComparator : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data) =
            oldItem.title == newItem.title

        override fun areContentsTheSame(oldItem: Data, newItem: Data) =
            oldItem == newItem
    }
}