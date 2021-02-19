package krasnikov.project.postsapp.post.common.data.source.remote

import krasnikov.project.postsapp.R
import krasnikov.project.postsapp.post.common.data.error.LoadedException
import krasnikov.project.postsapp.post.common.data.source.remote.api.PostService
import krasnikov.project.postsapp.post.common.data.source.remote.model.PostResponse
import krasnikov.project.postsapp.utils.AppMultithreading
import krasnikov.project.postsapp.utils.AsyncOperation
import krasnikov.project.postsapp.utils.Result
import retrofit2.Retrofit

class RemotePostDataSource(
    private val retrofit: Retrofit,
    private val multithreading: AppMultithreading
) {

    fun getAllPosts(): AsyncOperation<Result<List<PostResponse>>> {
        return multithreading.async {
            val response = retrofit.create(PostService::class.java).getPosts().execute()
            val postsResponse =
                response.body() ?: return@async Result.Error(
                    LoadedException(R.string.error_loading)
                )
            return@async Result.Success(postsResponse)
        }
    }
}