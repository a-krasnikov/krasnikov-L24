package krasnikov.project.postsapp.feed.post.data.source.remote.api

import krasnikov.project.postsapp.feed.post.data.source.remote.model.PostResponse
import retrofit2.Call
import retrofit2.http.GET

interface PostService {

    @GET("posts")
    fun getPosts(): Call<List<PostResponse>>
}