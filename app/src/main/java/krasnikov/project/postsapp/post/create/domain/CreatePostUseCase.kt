package krasnikov.project.postsapp.post.create.domain

import io.reactivex.Completable
import krasnikov.project.postsapp.post.common.data.PostRepository
import krasnikov.project.postsapp.post.common.data.model.PostEntity
import krasnikov.project.postsapp.post.create.domain.validate.PostValidator

class CreatePostUseCase(
    private val repository: PostRepository,
    private val postValidator: PostValidator
) {

    operator fun invoke(post: PostEntity): Completable {
        return Completable.fromAction { postValidator.validate(post) }
            .andThen(repository.savePost(post))
    }
}