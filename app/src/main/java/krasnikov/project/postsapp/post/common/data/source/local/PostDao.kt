package krasnikov.project.postsapp.post.common.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import krasnikov.project.postsapp.post.common.data.model.PostEntity

@Dao
interface PostDao {

    @Query("SELECT * FROM posts")
    fun observePosts(): LiveData<List<PostEntity>>

    @Insert
    fun insert(post: PostEntity)

    @Insert
    fun insertAll(posts: Collection<PostEntity>)

    @Query("DELETE FROM posts where is_local = 0")
    fun deleteRemotePosts()

    @Transaction
    fun updateRemotePosts(posts: Collection<PostEntity>) {
        deleteRemotePosts()
        insertAll(posts)
    }
}