package krasnikov.project.postsapp.app.di

import android.content.Context
import androidx.room.Room
import krasnikov.project.postsapp.app.App
import krasnikov.project.postsapp.app.db.AppDatabase
import krasnikov.project.postsapp.feed.post.data.source.local.dao.PostDao
import krasnikov.project.postsapp.feed.userstatus.data.dao.BannedUserDao
import krasnikov.project.postsapp.feed.userstatus.data.dao.WarningUserDao
import org.koin.dsl.module

private const val POSTS_DATABASE = "posts_database"

val dbModule = module {
    single { provideRoom(App.instance) }
    factory { providePostDao(get()) }
    factory { provideBannedUserDao(get()) }
    factory { provideWarningUserDao(get()) }
}

fun provideRoom(context: Context): AppDatabase {
    return Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java,
        POSTS_DATABASE
    ).fallbackToDestructiveMigration().build()
}

fun providePostDao(database: AppDatabase): PostDao {
    return database.postDao()
}

fun provideBannedUserDao(database: AppDatabase): BannedUserDao {
    return database.bannedUserDao()
}

fun provideWarningUserDao(database: AppDatabase): WarningUserDao {
    return database.warningUserDao()
}