package krasnikov.project.postsapp.post.common.data.source.remote.api

import krasnikov.project.postsapp.post.common.data.source.remote.model.PostResponse
import retrofit2.Call
import retrofit2.http.GET

interface PostService {

    @GET("posts")
    fun getPosts(): Call<List<PostResponse>>
}