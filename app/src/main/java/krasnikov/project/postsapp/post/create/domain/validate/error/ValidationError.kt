package krasnikov.project.postsapp.post.create.domain.validate.error

import androidx.annotation.StringRes

class ValidationError(@StringRes val stringRes: Int) : Exception()