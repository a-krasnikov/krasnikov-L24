package krasnikov.project.postsapp.userstatus.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "banned_users")
data class BannedUserEntity(
    @PrimaryKey
    val id: Long
)