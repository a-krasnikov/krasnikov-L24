package krasnikov.project.postsapp.post.common.data.source.remote

import io.reactivex.Single
import krasnikov.project.postsapp.post.common.data.model.PostResponse
import retrofit2.http.GET

interface PostService {

    @GET("posts")
    fun getPosts(): Single<List<PostResponse>>
}