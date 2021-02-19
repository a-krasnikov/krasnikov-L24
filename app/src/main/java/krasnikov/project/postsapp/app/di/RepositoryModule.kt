package krasnikov.project.postsapp.app.di

import krasnikov.project.postsapp.post.common.data.repository.PostRepository
import krasnikov.project.postsapp.userstatus.data.repository.UserStatusRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { PostRepository(get(), get(), get(), get()) }
    single { UserStatusRepository(get(), get()) }
}