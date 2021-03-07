package krasnikov.project.postsapp.app.di

import krasnikov.project.postsapp.post.create.domain.CreatePostUseCase
import krasnikov.project.postsapp.post.create.domain.validate.PostValidator
import krasnikov.project.postsapp.post.feed.domain.GetPostsUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module

val useCaseModule = module {
    factory {
        CreatePostUseCase(
            get(),
            get(),
            get(qualifier = named(Dispatcher.DEFAULT))
        )
    }
    factory { GetPostsUseCase(get(), get(qualifier = named(Dispatcher.DEFAULT))) }
}