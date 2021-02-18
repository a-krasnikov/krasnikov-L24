package krasnikov.project.postsapp.feed.post.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class PostResponse(
    @SerializedName("userId")
    val userId: Long,
    @SerializedName("title")
    val title: String,
    @SerializedName("body")
    val body: String
)