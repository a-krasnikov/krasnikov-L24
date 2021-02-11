package krasnikov.project.postsapp.postsfeed.post.data.source.remote

import krasnikov.project.postsapp.postsfeed.post.data.error.LoadedException
import krasnikov.project.postsapp.utils.Result
import krasnikov.project.postsapp.postsfeed.post.data.model.PostResponse
import krasnikov.project.postsapp.postsfeed.post.data.source.remote.api.PostService
import krasnikov.project.postsapp.utils.AppMultithreading
import krasnikov.project.postsapp.utils.AsyncOperation
import retrofit2.Retrofit

class RemotePostDataSourceImpl(
    private val retrofit: Retrofit,
    private val multithreading: AppMultithreading
) : RemotePostDataSource {

    override fun getAllPosts(): AsyncOperation<Result<List<PostResponse>>> {
        return multithreading.async {
            val response = retrofit.create(PostService::class.java).getPosts().execute()
            val postsResponse =
                response.body() ?: return@async Result.Error(LoadedException("Post not loaded"))
            return@async Result.Success(postsResponse)
        }
    }
}