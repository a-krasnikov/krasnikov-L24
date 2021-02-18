package krasnikov.project.postsapp.feed.post.data.mapper

import krasnikov.project.postsapp.feed.post.data.source.local.entity.PostEntity
import krasnikov.project.postsapp.feed.post.data.source.remote.model.PostResponse

class PostResponseMapper {

    fun map(source: PostResponse): PostEntity {
        return PostEntity(
            userId = source.userId,
            title = source.title,
            body = source.body,
            isLocal = false
        )
    }

    fun map(source: List<PostResponse>): List<PostEntity> {
        return source.map { map(it) }
    }
}