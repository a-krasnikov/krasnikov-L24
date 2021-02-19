package krasnikov.project.postsapp.post.create.ui

import androidx.lifecycle.ViewModel
import krasnikov.project.postsapp.post.common.data.source.local.entity.PostEntity
import krasnikov.project.postsapp.post.create.domain.CreatePostUseCase
import krasnikov.project.postsapp.utils.Result

class CreatePostViewModel(private val createPostUseCase: CreatePostUseCase) : ViewModel() {

    fun createPost(post: PostEntity): Result<Unit> {
        return createPostUseCase(post)
    }
}