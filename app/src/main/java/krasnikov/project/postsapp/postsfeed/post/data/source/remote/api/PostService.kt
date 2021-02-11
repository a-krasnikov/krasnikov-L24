package krasnikov.project.postsapp.postsfeed.post.data.source.remote.api

import krasnikov.project.postsapp.postsfeed.post.data.model.PostResponse
import retrofit2.Call
import retrofit2.http.GET

interface PostService {

    @GET("posts")
    fun getPosts(): Call<List<PostResponse>>
}