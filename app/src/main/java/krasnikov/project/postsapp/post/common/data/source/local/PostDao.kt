package krasnikov.project.postsapp.post.common.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import io.reactivex.Completable
import io.reactivex.Flowable
import krasnikov.project.postsapp.post.common.data.model.PostEntity

@Dao
abstract class PostDao {

    @Query("SELECT * FROM posts")
    abstract fun observePosts(): Flowable<List<PostEntity>>

    @Insert
    abstract fun insert(post: PostEntity): Completable

    @Insert
    protected abstract fun insertAll(posts: Collection<PostEntity>)

    @Query("DELETE FROM posts where is_local = 0")
    protected abstract fun deleteRemotePosts()

    fun updateRemotePosts(posts: Collection<PostEntity>): Completable {
        return Completable.fromAction {
            updateRemotePostsTransaction(posts)
        }
    }

    @Transaction
    protected open fun updateRemotePostsTransaction(posts: Collection<PostEntity>) {
        deleteRemotePosts()
        insertAll(posts)
    }
}