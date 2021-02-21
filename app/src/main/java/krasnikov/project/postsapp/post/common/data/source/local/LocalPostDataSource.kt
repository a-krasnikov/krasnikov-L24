package krasnikov.project.postsapp.post.common.data.source.local

import androidx.lifecycle.LiveData
import krasnikov.project.postsapp.post.common.data.model.PostEntity
import krasnikov.project.postsapp.utils.AppMultithreading
import krasnikov.project.postsapp.utils.Result
import krasnikov.project.postsapp.utils.mapAsync

class LocalPostDataSource(
    private val postDao: PostDao,
    private val multithreading: AppMultithreading
) {

    fun observePosts(): LiveData<List<PostEntity>> {
        return postDao.observePosts()
    }

    fun savePost(post: PostEntity) {
        multithreading.executeOnBackgroundThread {
            postDao.insert(post)
        }
    }

    fun refreshRemotePosts(posts: Collection<PostEntity>) {
        multithreading.executeOnBackgroundThread {
            postDao.updateRemotePosts(posts)
        }
    }
}