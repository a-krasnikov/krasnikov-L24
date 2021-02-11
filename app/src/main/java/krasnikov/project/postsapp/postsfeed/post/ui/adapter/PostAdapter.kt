package krasnikov.project.postsapp.postsfeed.post.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import krasnikov.project.postsapp.R
import krasnikov.project.postsapp.databinding.RecyclerItemPostBannedBinding
import krasnikov.project.postsapp.databinding.RecyclerItemPostBinding
import krasnikov.project.postsapp.postsfeed.post.ui.model.PostUIModel
import krasnikov.project.postsapp.utils.visibleOrGone

class PostAdapter : ListAdapter<PostUIModel, RecyclerView.ViewHolder>(PostDiffCallback()) {

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is PostUIModel.Post -> R.layout.recycler_item_post
            is PostUIModel.BannedPost -> R.layout.recycler_item_post_banned
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == R.layout.recycler_item_post) {
            PostViewHolder(
                RecyclerItemPostBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else {
            BannedPostViewHolder(
                RecyclerItemPostBannedBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PostViewHolder -> {
                holder.bind(getItem(position) as PostUIModel.Post)
            }
            is BannedPostViewHolder -> {
                holder.bind(getItem(position) as PostUIModel.BannedPost)
            }
        }
    }

    private class PostViewHolder(private val binding: RecyclerItemPostBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(post: PostUIModel.Post) {
            val resources = itemView.resources

            binding.tvUserId.text = resources.getString(R.string.item_user_id, post.userId)
            binding.tvTitle.text = post.title
            binding.tvBody.text = post.body
            binding.root.setBackgroundColor(post.backgroundColor)
            binding.tvWarning.visibleOrGone(post.isVisibleWarning)
        }
    }

    private class BannedPostViewHolder(private val binding: RecyclerItemPostBannedBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(post: PostUIModel.BannedPost) {
            val resources = itemView.resources

            binding.tvTitle.text = resources.getString(R.string.title_banned_post, post.userId)
        }
    }

    private class PostDiffCallback : DiffUtil.ItemCallback<PostUIModel>() {
        override fun areItemsTheSame(oldItem: PostUIModel, newItem: PostUIModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PostUIModel, newItem: PostUIModel): Boolean {
            return oldItem == newItem
        }
    }
}