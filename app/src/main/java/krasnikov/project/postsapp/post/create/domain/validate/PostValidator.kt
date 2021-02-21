package krasnikov.project.postsapp.post.create.domain.validate

import krasnikov.project.postsapp.R
import krasnikov.project.postsapp.post.common.data.model.PostEntity
import krasnikov.project.postsapp.post.create.domain.validate.error.ValidationError
import krasnikov.project.postsapp.utils.Result

class PostValidator {

    fun validate(post: PostEntity): Result<Unit> {
        try {
            validateTitle(post.title)
            validateBody(post.body)
        } catch (ex: Exception) {
            return Result.Error(ex)
        }
        return Result.Success(Unit)
    }

    private fun validateTitle(title: String) {
        if (title.length !in (3..50)) {
            throw ValidationError(R.string.error_post_title_length_validation)
        }

        val pattern = "(?iu)Реклама|Товар|Куплю"
        if (title.matches(Regex(pattern))) {
            throw ValidationError(R.string.error_post_title_content_validation)
        }
    }

    private fun validateBody(body: String) {
        if (body.length !in (5..500)) {
            throw ValidationError(R.string.error_post_body_length_validation)
        }
    }
}