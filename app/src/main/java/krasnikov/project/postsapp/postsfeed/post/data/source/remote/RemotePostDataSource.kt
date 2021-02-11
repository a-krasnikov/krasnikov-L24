package krasnikov.project.postsapp.postsfeed.post.data.source.remote

import krasnikov.project.postsapp.utils.Result
import krasnikov.project.postsapp.postsfeed.post.data.model.PostResponse
import krasnikov.project.postsapp.utils.AsyncOperation

interface RemotePostDataSource {
    fun getAllPosts(): AsyncOperation<Result<List<PostResponse>>>
}