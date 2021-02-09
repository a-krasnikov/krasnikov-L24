package krasnikov.project.postsapp.data.repository

import krasnikov.project.postsapp.data.Result
import krasnikov.project.postsapp.data.model.PostResponse
import krasnikov.project.postsapp.data.source.PostDataSource
import krasnikov.project.postsapp.utils.AppExecutor
import krasnikov.project.postsapp.utils.AsyncOperation

class PostRepository(
    private val executor: AppExecutor,
    private val remoteDataSource: PostDataSource
) {

    fun getPosts(): AsyncOperation<Result<List<PostResponse>>> {
        return remoteDataSource.getAllPosts()
    }
}