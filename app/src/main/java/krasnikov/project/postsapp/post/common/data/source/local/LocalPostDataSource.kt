package krasnikov.project.postsapp.post.common.data.source.local

import kotlinx.coroutines.flow.Flow
import krasnikov.project.postsapp.post.common.data.model.PostEntity

class LocalPostDataSource(private val postDao: PostDao) {

    fun observePosts(): Flow<List<PostEntity>> {
        return postDao.observePosts()
    }

    suspend fun savePost(post: PostEntity) {
        postDao.insert(post)
    }

    suspend fun refreshRemotePosts(posts: Collection<PostEntity>) {
        postDao.updateRemotePosts(posts)
    }
}