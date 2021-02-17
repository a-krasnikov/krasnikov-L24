package krasnikov.project.postsapp.app.di

import krasnikov.project.postsapp.feed.post.data.repository.PostRepository
import krasnikov.project.postsapp.feed.userstatus.data.repository.UserStatusRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { PostRepository(get(), get(), get(), get()) }
    single { UserStatusRepository(get(), get(), get()) }
}