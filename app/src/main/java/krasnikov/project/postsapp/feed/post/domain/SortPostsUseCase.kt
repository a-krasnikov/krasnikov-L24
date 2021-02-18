package krasnikov.project.postsapp.feed.post.domain

import krasnikov.project.postsapp.feed.post.domain.model.PostModel

class SortPostsUseCase {
    fun invoke(posts: List<PostModel>): List<PostModel> {
        val (local, remote) = posts.partition { it.isLocal }
        return local.sortedByDescending { it.id } + remote.sortedBy { it.id }
    }
}