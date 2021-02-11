package krasnikov.project.postsapp.postsfeed.post.data.mapper

import krasnikov.project.postsapp.postsfeed.post.data.model.PostResponse
import krasnikov.project.postsapp.postsfeed.post.domain.model.PostModel
import krasnikov.project.postsapp.postsfeed.userstatus.data.repository.UserStatusRepository
import krasnikov.project.postsapp.utils.Result

class PostDomainMapper(private val userStatusRepository: UserStatusRepository) {

    fun map(source: Result<PostResponse>): Result<PostModel> {
        return when (source) {
            is Result.Success -> {
                Result.Success(mapData(source.data))
            }
            is Result.Error -> {
                source
            }
        }
    }

    fun mapList(source: Result<List<PostResponse>>): Result<List<PostModel>> {
        return when (source) {
            is Result.Success -> {
                Result.Success(source.data.map { mapData(it) })
            }
            is Result.Error -> {
                source
            }
        }
    }

    private fun mapData(source: PostResponse): PostModel {
        return PostModel(
            id = source.id,
            userId = source.userId,
            title = source.title,
            body = source.body,
            status = userStatusRepository.getUserStatusById(source.userId)
        )
    }
}