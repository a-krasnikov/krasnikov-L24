package krasnikov.project.postsapp.post.common.domain.model

import krasnikov.project.postsapp.userstatus.data.UserStatus

data class PostModel(
    val id: Long,
    val userId: Long,
    val title: String,
    val body: String,
    val isLocal: Boolean,
    val status: UserStatus,
)
