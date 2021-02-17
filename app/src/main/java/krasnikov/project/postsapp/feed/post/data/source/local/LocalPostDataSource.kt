package krasnikov.project.postsapp.feed.post.data.source.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import krasnikov.project.postsapp.feed.post.data.source.local.dao.PostDao
import krasnikov.project.postsapp.feed.post.data.source.local.entity.PostEntity
import krasnikov.project.postsapp.utils.AppMultithreading
import krasnikov.project.postsapp.utils.AsyncOperation
import krasnikov.project.postsapp.utils.Result

class LocalPostDataSource(
    private val postDao: PostDao,
    private val multithreading: AppMultithreading
) {

    fun getPosts(): AsyncOperation<Result<List<PostEntity>>> {
        return multithreading.async {
            return@async try {
                Result.Success(postDao.getPosts())
            } catch (ex: Exception) {
                Result.Error(ex)
            }
        }
    }

    fun deleteAllPosts() {
        multithreading.async {
            postDao.deletePosts()
        }
    }

    fun savePost(post: PostEntity) {
        multithreading.async {
            postDao.insert(post)
        }.postOnMainThread {}
    }

    fun savePosts(posts: Collection<PostEntity>) {
        multithreading.async {
            postDao.insertAll(posts)
        }.postOnMainThread {}
    }
}