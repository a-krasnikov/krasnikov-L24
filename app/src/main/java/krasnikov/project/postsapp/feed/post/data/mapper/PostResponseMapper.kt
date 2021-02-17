package krasnikov.project.postsapp.feed.post.data.mapper

import krasnikov.project.postsapp.feed.post.data.source.local.entity.PostEntity
import krasnikov.project.postsapp.feed.post.data.source.remote.model.PostResponse
import krasnikov.project.postsapp.feed.post.domain.model.PostModel
import krasnikov.project.postsapp.feed.userstatus.data.repository.UserStatusRepository

class PostResponseMapper() {

    fun map(source: PostResponse): PostEntity {
        return PostEntity(
            id = source.id,
            userId = source.userId,
            title = source.title,
            body = source.body,
        )
    }

    fun map(source: List<PostResponse>): List<PostEntity> {
        return source.map { map(it) }
    }
}