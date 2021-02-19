package krasnikov.project.postsapp.app.di

import android.content.Context
import androidx.room.Room
import krasnikov.project.postsapp.app.App
import krasnikov.project.postsapp.app.db.AppDatabase
import krasnikov.project.postsapp.post.common.data.source.local.dao.PostDao
import krasnikov.project.postsapp.userstatus.data.dao.BannedUserDao
import krasnikov.project.postsapp.userstatus.data.dao.WarningUserDao
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dbModule = module {
    single { provideDb(androidContext()) }
    factory { providePostDao(get()) }
    factory { provideBannedUserDao(get()) }
    factory { provideWarningUserDao(get()) }
}

fun provideDb(context: Context): AppDatabase {
    return AppDatabase.getInstance(context)
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