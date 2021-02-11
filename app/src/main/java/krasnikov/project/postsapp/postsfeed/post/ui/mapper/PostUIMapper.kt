package krasnikov.project.postsapp.postsfeed.post.ui.mapper

import android.graphics.Color
import krasnikov.project.postsapp.postsfeed.post.domain.model.PostModel
import krasnikov.project.postsapp.postsfeed.post.domain.model.Status
import krasnikov.project.postsapp.postsfeed.post.ui.model.PostUIModel
import krasnikov.project.postsapp.utils.Result

class PostUIMapper {

    fun map(source: Result<PostModel>): Result<PostUIModel> {
        return when (source) {
            is Result.Success -> {
                Result.Success(mapData(source.data))
            }
            is Result.Error -> {
                source
            }
        }
    }

    fun mapList(source: Result<List<PostModel>>): Result<List<PostUIModel>> {
        return when (source) {
            is Result.Success -> {
                Result.Success(source.data.map { mapData(it) })
            }
            is Result.Error -> {
                source
            }
        }
    }

    private fun mapData(source: PostModel): PostUIModel {
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
}