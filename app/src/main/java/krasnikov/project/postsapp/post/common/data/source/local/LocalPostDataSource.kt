package krasnikov.project.postsapp.post.common.data.source.local

import io.reactivex.Completable
import io.reactivex.Flowable
import krasnikov.project.postsapp.post.common.data.model.PostEntity

class LocalPostDataSource(private val postDao: PostDao) {

    fun observePosts(): Flowable<List<PostEntity>> {
        return postDao.observePosts()
    }

    fun savePost(post: PostEntity): Completable {
        return postDao.insert(post)
    }

    fun refreshRemotePosts(posts: Collection<PostEntity>): Completable {
        return postDao.updateRemotePosts(posts)
    }
}