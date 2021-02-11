package krasnikov.project.postsapp.app.di

import krasnikov.project.postsapp.postsfeed.post.data.repository.PostRepository
import krasnikov.project.postsapp.postsfeed.post.data.repository.PostRepositoryImpl
import krasnikov.project.postsapp.postsfeed.userstatus.data.repository.UserStatusRepository
import krasnikov.project.postsapp.postsfeed.userstatus.data.repository.UserStatusRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<PostRepository> { PostRepositoryImpl(get(), get()) }
    single<UserStatusRepository> { UserStatusRepositoryImpl(get()) }
}