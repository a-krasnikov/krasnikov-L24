package krasnikov.project.postsapp.post.feed.ui.mapper

import android.graphics.Color
import krasnikov.project.postsapp.post.common.domain.model.PostModel
import krasnikov.project.postsapp.post.feed.ui.model.PostUIModel
import krasnikov.project.postsapp.userstatus.data.UserStatus

class PostUIMapper {

    fun map(source: PostModel): PostUIModel {
        return when (source.status) {
            UserStatus.STANDART -> {
                PostUIModel.Post(
                    id = source.id,
                    userId = source.userId,
                    title = source.title,
                    body = source.body,
                    backgroundColor = Color.TRANSPARENT,
                    isVisibleWarning = false
                )
            }
            UserStatus.WITH_WARNING -> {
                PostUIModel.Post(
                    id = source.id,
                    userId = source.userId,
                    title = source.title,
                    body = source.body,
                    backgroundColor = Color.RED,
                    isVisibleWarning = true
                )
            }
            UserStatus.BANNED -> {
                PostUIModel.BannedPost(id = source.id, userId = source.userId)
            }
        }
    }

    fun map(source: List<PostModel>): List<PostUIModel> {
        return source.map { map(it) }
    }
}