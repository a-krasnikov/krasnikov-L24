package krasnikov.project.postsapp.post.common.data.source.remote

import io.reactivex.Single
import krasnikov.project.postsapp.post.common.data.model.PostResponse
import krasnikov.project.postsapp.utils.AppMultithreading
import retrofit2.Retrofit

class RemotePostDataSource(
    private val retrofit: Retrofit,
    private val multithreading: AppMultithreading
) {

    fun getAllPosts(): Single<List<PostResponse>> =
        retrofit.create(PostService::class.java).getPosts()
}