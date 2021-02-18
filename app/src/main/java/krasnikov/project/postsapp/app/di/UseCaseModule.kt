package krasnikov.project.postsapp.app.di

import krasnikov.project.postsapp.feed.post.domain.validate.PostValidator
import krasnikov.project.postsapp.feed.post.domain.SavePostUseCase
import krasnikov.project.postsapp.feed.post.domain.SortPostsUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { SavePostUseCase(get(), providePostValidator()) }
    factory { SortPostsUseCase() }
}

fun providePostValidator(): PostValidator = PostValidator()
