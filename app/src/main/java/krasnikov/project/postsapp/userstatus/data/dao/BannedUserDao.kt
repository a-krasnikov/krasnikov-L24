package krasnikov.project.postsapp.userstatus.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import krasnikov.project.postsapp.userstatus.data.entity.BannedUserEntity

@Dao
interface BannedUserDao {
    @Query("SELECT * FROM banned_users")
    fun getAll(): List<BannedUserEntity>

    @Insert
    fun insert(user: BannedUserEntity): Completable
}