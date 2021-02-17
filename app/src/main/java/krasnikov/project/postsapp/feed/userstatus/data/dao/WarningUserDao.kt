package krasnikov.project.postsapp.feed.userstatus.data.dao

import androidx.room.Dao
import androidx.room.Query
import krasnikov.project.postsapp.feed.userstatus.data.entity.WarningUserEntity

@Dao
interface WarningUserDao {
    @Query("SELECT * FROM warning_users")
    fun getAll(): List<WarningUserEntity>
}