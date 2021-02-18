package krasnikov.project.postsapp.feed.post.data.source.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import krasnikov.project.postsapp.feed.post.data.source.local.dao.PostDao
import krasnikov.project.postsapp.feed.post.data.source.local.entity.PostEntity
import krasnikov.project.postsapp.utils.AppMultithreading
import krasnikov.project.postsapp.utils.AsyncOperation
import krasnikov.project.postsapp.utils.Result
import krasnikov.project.postsapp.utils.mapAsync

class LocalPostDataSource(
    private val postDao: PostDao,
    private val multithreading: AppMultithreading
) {

    fun observePosts(): LiveData<Result<List<PostEntity>>> {
        return postDao.observePosts().mapAsync { Result.Success(it) }
    }

    fun savePost(post: PostEntity) {
        multithreading.executeOnBackgroundThread {
            postDao.insert(post)
        }
    }

    fun refreshRemotePosts(posts: Collection<PostEntity>) {
        multithreading.executeOnBackgroundThread {
            postDao.deleteRemotePosts()
            postDao.insertAll(posts)
        }
    }
}