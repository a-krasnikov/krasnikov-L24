package krasnikov.project.postsapp.feed.post.domain

import krasnikov.project.postsapp.feed.post.data.repository.PostRepository
import krasnikov.project.postsapp.feed.post.domain.model.PostModel
import krasnikov.project.postsapp.utils.Result

class SavePostUseCase(
    private val repository: PostRepository,
    private val postValidator: PostValidator
) {

    fun invoke(post: PostModel): Result<Unit> {
        postValidator.validate(post)
        repository.savePost(post)

        return Result.Success(Unit)
    }
}