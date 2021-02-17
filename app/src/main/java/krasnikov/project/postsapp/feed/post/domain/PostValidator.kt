package krasnikov.project.postsapp.feed.post.domain

import krasnikov.project.postsapp.feed.post.domain.model.PostModel
import krasnikov.project.postsapp.utils.Result

class PostValidator {

    fun validate(post: PostModel): Result<Unit> {

        validateTitle(post.title)
        validateBody(post.body)

        return Result.Success(Unit)
    }

    private fun validateTitle(title: String) {

    }

    private fun validateBody(body: String) {

    }
}