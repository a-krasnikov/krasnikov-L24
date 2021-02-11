package krasnikov.project.postsapp.postsfeed.post.data.repository

import krasnikov.project.postsapp.postsfeed.post.domain.model.PostModel
import krasnikov.project.postsapp.utils.AsyncOperation
import krasnikov.project.postsapp.utils.Result

interface PostRepository {
    fun getPosts(): AsyncOperation<Result<List<PostModel>>>
}