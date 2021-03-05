package krasnikov.project.postsapp.post.common.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import krasnikov.project.postsapp.post.common.data.model.PostEntity

@Dao
interface PostDao {

    @Query("SELECT * FROM posts")
    fun observePosts(): Flow<List<PostEntity>>

    @Insert
    suspend fun insert(post: PostEntity)

    @Insert
    suspend fun insertAll(posts: Collection<PostEntity>)

    @Query("DELETE FROM posts where is_local = 0")
    suspend fun deleteRemotePosts()

    @Transaction
    suspend fun updateRemotePosts(posts: Collection<PostEntity>) {
        deleteRemotePosts()
        insertAll(posts)
    }
}