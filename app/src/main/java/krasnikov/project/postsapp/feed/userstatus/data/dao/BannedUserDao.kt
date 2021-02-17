package krasnikov.project.postsapp.feed.userstatus.data.dao

import androidx.room.Dao
import androidx.room.Query
import krasnikov.project.postsapp.feed.userstatus.data.entity.BannedUserEntity

@Dao
interface BannedUserDao {
    @Query("SELECT * FROM banned_users")
    fun getAll(): List<BannedUserEntity>
}