package krasnikov.project.postsapp.data.source

import krasnikov.project.postsapp.data.Result
import krasnikov.project.postsapp.data.model.PostResponse
import krasnikov.project.postsapp.utils.AsyncOperation

interface PostDataSource {
    fun getAllPosts(): AsyncOperation<Result<List<PostResponse>>>
}