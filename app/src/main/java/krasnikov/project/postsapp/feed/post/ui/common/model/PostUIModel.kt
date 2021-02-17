package krasnikov.project.postsapp.feed.post.ui.common.model

import androidx.annotation.ColorInt

sealed class PostUIModel {
    abstract val id: Long

    data class Post(
        override val id: Long,
        val userId: Long,
        val title: String,
        val body: String,
        @ColorInt val backgroundColor: Int,
        val isVisibleWarning: Boolean
    ) : PostUIModel()

    data class BannedPost(
        override val id: Long,
        val userId: Long
    ) : PostUIModel()
}