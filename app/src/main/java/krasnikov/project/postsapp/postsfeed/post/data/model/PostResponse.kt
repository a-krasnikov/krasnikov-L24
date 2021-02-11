package krasnikov.project.postsapp.postsfeed.post.data.model

import com.google.gson.annotations.SerializedName

data class PostResponse(
    @SerializedName("id")
    val id: Long,
    @SerializedName("userId")
    val userId: Long,
    @SerializedName("title")
    val title: String,
    @SerializedName("body")
    val body: String
)