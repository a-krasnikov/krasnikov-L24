package krasnikov.project.postsapp.post.common.data.mapper

import krasnikov.project.postsapp.post.common.data.model.PostEntity
import krasnikov.project.postsapp.post.common.data.model.PostResponse

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
