package krasnikov.project.postsapp.feed.post.domain

import krasnikov.project.postsapp.feed.post.data.repository.PostRepository
import krasnikov.project.postsapp.feed.post.data.source.local.entity.PostEntity
import krasnikov.project.postsapp.feed.post.domain.validate.PostValidator
import krasnikov.project.postsapp.utils.Result

class SavePostUseCase(
    private val repository: PostRepository,
    private val postValidator: PostValidator
) {

    fun invoke(post: PostEntity): Result<Unit> {
        val validationResult = postValidator.validate(post)

        if (validationResult is Result.Success)
            repository.savePost(post)

        return validationResult
    }
}