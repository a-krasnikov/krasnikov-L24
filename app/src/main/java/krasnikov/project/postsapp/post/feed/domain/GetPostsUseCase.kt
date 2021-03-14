package krasnikov.project.postsapp.post.feed.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import krasnikov.project.postsapp.post.common.data.PostRepository
import krasnikov.project.postsapp.post.common.domain.PostModel

class GetPostsUseCase(
    private val postRepository: PostRepository,
    private val defaultDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(): Flow<List<PostModel>> {
        return postRepository.observePosts().map { posts ->
            val (local, remote) = posts.partition { it.isLocal }
            local.sortedByDescending { it.id } + remote.sortedBy { it.id }
        }.flowOn(defaultDispatcher)
    }
}
