package krasnikov.project.postsapp.data.source.remote

import krasnikov.project.postsapp.data.Result
import krasnikov.project.postsapp.data.model.PostResponse
import krasnikov.project.postsapp.data.source.PostDataSource
import krasnikov.project.postsapp.data.source.remote.api.PostService
import krasnikov.project.postsapp.utils.AppExecutor
import krasnikov.project.postsapp.utils.AsyncOperation
import java.lang.Exception

class RemotePostDataSource(
    private val postService: PostService,
    private val executors: AppExecutor
) : PostDataSource {
    override fun getAllPosts(): AsyncOperation<Result<List<PostResponse>>> {
        return executors.async {
            val response = postService.getPosts().execute()
            // todo exception
            val postsResponse = response.body() ?: return@async Result.Error(Exception())
            return@async Result.Success(postsResponse)
        }
    }
}