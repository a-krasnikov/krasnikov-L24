package krasnikov.project.postsapp.post.feed.ui.mapper

import android.graphics.Color
import krasnikov.project.postsapp.post.common.domain.PostModel
import krasnikov.project.postsapp.post.feed.ui.model.PostUIModel
import krasnikov.project.postsapp.userstatus.data.UserStatus

class PostUIMapper {

    fun map(source: PostModel): PostUIModel {
        return when (source.status) {
            UserStatus.BANNED -> {
                PostUIModel.BannedPost(id = source.id, userId = source.userId)
            }
            else -> {
                PostUIModel.Post(
                    id = source.id,
                    userId = source.userId,
                    title = source.title,
                    body = source.body,
                    backgroundColor = if (source.status == UserStatus.WITH_WARNING) Color.RED else Color.TRANSPARENT,
                    isVisibleWarning = source.status == UserStatus.WITH_WARNING
                )
            }
        }
    }

    fun map(source: List<PostModel>): List<PostUIModel> {
        return source.map { map(it) }
    }
}
