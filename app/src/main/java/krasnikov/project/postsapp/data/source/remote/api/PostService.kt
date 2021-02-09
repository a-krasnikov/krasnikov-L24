package krasnikov.project.postsapp.data.source.remote.api

import krasnikov.project.postsapp.data.model.PostResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface PostService {

    @GET("posts")
    fun getPosts(): Call<List<PostResponse>>

    companion object {
        private const val baseUrl = "https://jsonplaceholder.typicode.com/"

        private val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        fun providePostService(): PostService =
            retrofit.create(PostService::class.java)
    }
}