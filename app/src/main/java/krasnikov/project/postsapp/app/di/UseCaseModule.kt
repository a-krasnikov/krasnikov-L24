package krasnikov.project.postsapp.app.di

import krasnikov.project.postsapp.post.create.domain.CreatePostUseCase
import krasnikov.project.postsapp.post.create.domain.validate.PostValidator
import krasnikov.project.postsapp.post.feed.domain.SortPostsUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { CreatePostUseCase(get(), providePostValidator()) }
    factory { SortPostsUseCase() }
}

fun providePostValidator(): PostValidator = PostValidator()
