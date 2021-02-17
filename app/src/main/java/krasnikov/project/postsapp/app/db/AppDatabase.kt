package krasnikov.project.postsapp.app.db

import androidx.room.Database
import androidx.room.RoomDatabase
import krasnikov.project.postsapp.feed.post.data.source.local.dao.PostDao
import krasnikov.project.postsapp.feed.post.data.source.local.entity.PostEntity
import krasnikov.project.postsapp.feed.userstatus.data.dao.BannedUserDao
import krasnikov.project.postsapp.feed.userstatus.data.dao.WarningUserDao
import krasnikov.project.postsapp.feed.userstatus.data.entity.BannedUserEntity
import krasnikov.project.postsapp.feed.userstatus.data.entity.WarningUserEntity

@Database(
    entities = [PostEntity::class, BannedUserEntity::class, WarningUserEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun postDao(): PostDao
    abstract fun bannedUserDao(): BannedUserDao
    abstract fun warningUserDao(): WarningUserDao
}