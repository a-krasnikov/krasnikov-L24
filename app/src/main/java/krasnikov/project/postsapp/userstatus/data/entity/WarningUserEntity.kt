package krasnikov.project.postsapp.userstatus.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "warning_users")
data class WarningUserEntity(
    @PrimaryKey
    val id: Long
)
