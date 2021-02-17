package krasnikov.project.postsapp.feed.post.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import krasnikov.project.postsapp.feed.post.data.source.local.entity.PostEntity

@Dao
interface PostDao {

    @Query("SELECT * FROM posts")
    fun getPosts(): List<PostEntity>

    @Insert
    fun insert(post: PostEntity)

    @Insert
    fun insertAll(posts: Collection<PostEntity>)

    @Query("DELETE FROM posts")
    fun deletePosts()
}