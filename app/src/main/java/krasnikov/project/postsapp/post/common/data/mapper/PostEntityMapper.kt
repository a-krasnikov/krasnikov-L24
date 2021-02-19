package krasnikov.project.postsapp.post.common.data.mapper

import krasnikov.project.postsapp.post.common.data.source.local.entity.PostEntity
import krasnikov.project.postsapp.post.common.domain.model.PostModel
import krasnikov.project.postsapp.userstatus.data.repository.UserStatusRepository

class PostEntityMapper(private val userStatusRepository: UserStatusRepository) {

    fun map(source: PostEntity): PostModel {
        return PostModel(
            id = source.id,
            userId = source.userId,
            title = source.title,
            body = source.body,
            isLocal = source.isLocal,
            status = userStatusRepository.getUserStatusById(source.userId)
        )
    }

    fun map(source: List<PostEntity>): List<PostModel> {
        return source.map { map(it) }
    }
}