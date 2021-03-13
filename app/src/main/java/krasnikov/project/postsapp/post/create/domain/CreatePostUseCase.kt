package krasnikov.project.postsapp.post.create.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import krasnikov.project.postsapp.post.common.data.PostRepository
import krasnikov.project.postsapp.post.common.data.model.PostEntity
import krasnikov.project.postsapp.post.create.domain.validate.PostValidator

class CreatePostUseCase(
    private val repository: PostRepository,
    private val postValidator: PostValidator,
    private val defaultDispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(post: PostEntity) {
        withContext(defaultDispatcher) {
            postValidator.validate(post)
            repository.savePost(post)
        }
    }
}
