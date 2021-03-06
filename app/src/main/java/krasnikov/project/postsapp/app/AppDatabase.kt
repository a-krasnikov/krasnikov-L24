package krasnikov.project.postsapp.app

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import krasnikov.project.postsapp.post.common.data.source.local.PostDao
import krasnikov.project.postsapp.post.common.data.model.PostEntity
import krasnikov.project.postsapp.userstatus.data.dao.BannedUserDao
import krasnikov.project.postsapp.userstatus.data.dao.WarningUserDao
import krasnikov.project.postsapp.userstatus.data.entity.BannedUserEntity
import krasnikov.project.postsapp.userstatus.data.entity.WarningUserEntity

@Database(
    entities = [PostEntity::class, BannedUserEntity::class, WarningUserEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun postDao(): PostDao
    abstract fun bannedUserDao(): BannedUserDao
    abstract fun warningUserDao(): WarningUserDao


    companion object {
        private const val POSTS_DATABASE = "posts_database"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                POSTS_DATABASE
            ).addCallback(object : Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    getInstance(context).run {
                        GlobalScope.launch {
                            bannedUserDao().insert(BannedUserEntity(2))
                            warningUserDao().insert(WarningUserEntity(1))
                        }
                    }
                }
            }).build()
    }
}