package krasnikov.project.postsapp.post.create.domain.validate

import android.content.res.Resources
import krasnikov.project.postsapp.R
import krasnikov.project.postsapp.post.common.data.model.PostEntity
import krasnikov.project.postsapp.post.create.domain.validate.error.ValidationError

class PostValidator(private val resources: Resources) {

    @Suppress("MagicNumber")
    private val validTitleLength = 3..50
    @Suppress("MagicNumber")
    private val validBodyLength = 5..500

    fun validate(post: PostEntity) {
        validateTitle(post.title)
        validateBody(post.body)
    }

    private fun validateTitle(title: String) {
        if (title.length !in validTitleLength) {
            throw ValidationError(R.string.error_post_title_length_validation)
        }

        val pattern = resources.getString(R.string.validate_pattern_title)
        if (title.matches(Regex(pattern))) {
            throw ValidationError(R.string.error_post_title_content_validation)
        }
    }

    private fun validateBody(body: String) {
        if (body.length !in validBodyLength) {
            throw ValidationError(R.string.error_post_body_length_validation)
        }
    }
}
