package krasnikov.project.postsapp.post.create.domain

import krasnikov.project.postsapp.post.common.data.PostRepository
import krasnikov.project.postsapp.post.common.data.model.PostEntity
import krasnikov.project.postsapp.post.create.domain.validate.PostValidator
import krasnikov.project.postsapp.utils.Result

class CreatePostUseCase(
    private val repository: PostRepository,
    private val postValidator: PostValidator
) {

    operator fun invoke(post: PostEntity): Result<Unit> {
        val validationResult = postValidator.validate(post)

        if (validationResult is Result.Success)
            repository.savePost(post)

        return validationResult
    }
}