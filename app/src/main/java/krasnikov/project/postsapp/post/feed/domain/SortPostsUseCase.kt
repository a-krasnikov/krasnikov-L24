package krasnikov.project.postsapp.post.feed.domain

import krasnikov.project.postsapp.post.common.domain.PostModel

class SortPostsUseCase {
    operator fun invoke(posts: List<PostModel>): List<PostModel> {
        val (local, remote) = posts.partition { it.isLocal }
        return local.sortedByDescending { it.id } + remote.sortedBy { it.id }
    }
}