package krasnikov.project.postsapp.userstatus.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import krasnikov.project.postsapp.userstatus.data.entity.WarningUserEntity

@Dao
interface WarningUserDao {
    @Query("SELECT * FROM warning_users")
    fun getAll(): List<WarningUserEntity>

    @Insert
    fun insert(user: WarningUserEntity)
}