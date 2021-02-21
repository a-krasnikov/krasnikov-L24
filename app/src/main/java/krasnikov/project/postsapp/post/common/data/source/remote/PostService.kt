package krasnikov.project.postsapp.post.common.data.source.remote

import krasnikov.project.postsapp.post.common.data.model.PostResponse
import retrofit2.Call
import retrofit2.http.GET

interface PostService {

    @GET("posts")
    fun getPosts(): Call<List<PostResponse>>
}