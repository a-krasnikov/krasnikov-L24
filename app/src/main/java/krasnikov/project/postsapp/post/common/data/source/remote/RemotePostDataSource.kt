package krasnikov.project.postsapp.post.common.data.source.remote

import krasnikov.project.postsapp.post.common.data.model.PostResponse
import retrofit2.Retrofit

class RemotePostDataSource(private val retrofit: Retrofit) {

    suspend fun getAllPosts(): List<PostResponse> =
        retrofit.create(PostService::class.java).getPosts()
}
