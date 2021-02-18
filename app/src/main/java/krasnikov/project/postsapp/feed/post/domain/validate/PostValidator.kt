package krasnikov.project.postsapp.feed.post.domain.validate

import krasnikov.project.postsapp.feed.post.data.source.local.entity.PostEntity
import krasnikov.project.postsapp.feed.post.domain.validate.error.ErrorValidateBody
import krasnikov.project.postsapp.feed.post.domain.validate.error.ErrorValidateTitle
import krasnikov.project.postsapp.utils.Result
import java.lang.Exception

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
            throw ErrorValidateTitle("ErrorValidateTitle")
        }

        val pattern = "(?iu)Реклама|Товар|Куплю"
        if (title.matches(Regex(pattern))) {
            throw ErrorValidateTitle("")
        }
    }

    private fun validateBody(body: String) {
        if (body.length !in (5..500)) {
            throw ErrorValidateBody("ErrorValidateBody")
        }
    }
}