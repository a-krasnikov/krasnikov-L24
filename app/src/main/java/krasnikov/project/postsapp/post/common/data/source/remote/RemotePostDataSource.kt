package krasnikov.project.postsapp.post.common.data.source.remote

import io.reactivex.Single
import krasnikov.project.postsapp.post.common.data.model.PostResponse
import retrofit2.Retrofit

class RemotePostDataSource(private val retrofit: Retrofit) {

    fun getAllPosts(): Single<List<PostResponse>> =
        retrofit.create(PostService::class.java).getPosts()
}