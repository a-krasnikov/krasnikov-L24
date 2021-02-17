package krasnikov.project.postsapp.feed.post.ui.common.mapper

import android.graphics.Color
import krasnikov.project.postsapp.feed.post.domain.model.PostModel
import krasnikov.project.postsapp.feed.post.domain.model.Status
import krasnikov.project.postsapp.feed.post.ui.common.model.PostUIModel
import krasnikov.project.postsapp.utils.Result

class PostUIMapper {

    fun map(source: PostModel): PostUIModel {
        return when (source.status) {
            Status.STANDART -> {
                PostUIModel.Post(
                    id = source.id,
                    userId = source.userId,
                    title = source.title,
                    body = source.body,
                    backgroundColor = Color.TRANSPARENT,
                    isVisibleWarning = false
                )
            }
            Status.WITH_WARNING -> {
                PostUIModel.Post(
                    id = source.id,
                    userId = source.userId,
                    title = source.title,
                    body = source.body,
                    backgroundColor = Color.RED,
                    isVisibleWarning = true
                )
            }
            Status.BANNED -> {
                PostUIModel.BannedPost(id = source.id, userId = source.userId)
            }
        }
    }

    fun map(source: List<PostModel>): List<PostUIModel> {
        return source.map { map(it) }
    }
}