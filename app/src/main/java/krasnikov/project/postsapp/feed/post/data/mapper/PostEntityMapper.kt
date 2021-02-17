package krasnikov.project.postsapp.feed.post.data.mapper

import krasnikov.project.postsapp.feed.post.data.source.local.entity.PostEntity
import krasnikov.project.postsapp.feed.post.domain.model.PostModel
import krasnikov.project.postsapp.feed.userstatus.data.repository.UserStatusRepository

class PostEntityMapper(private val userStatusRepository: UserStatusRepository) {

    fun mapToPostModel(source: PostEntity): PostModel {
        return PostModel(
            id = source.id,
            userId = source.userId,
            title = source.title,
            body = source.body,
            status = userStatusRepository.getUserStatusById(source.userId)
        )
    }

    fun mapToPostModel(source: List<PostEntity>): List<PostModel> {
        return source.map { mapToPostModel(it) }
    }

    fun mapFromPostModel(source: PostModel): PostEntity {
        return PostEntity(
            id = source.id,
            userId = source.userId,
            title = source.title,
            body = source.body,
        )
    }

    fun mapFromPostModel(source: List<PostModel>): List<PostEntity> {
        return source.map { mapFromPostModel(it) }
    }
}