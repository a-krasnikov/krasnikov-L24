package krasnikov.project.postsapp.app.di

import krasnikov.project.postsapp.feed.post.data.mapper.PostEntityMapper
import krasnikov.project.postsapp.feed.post.data.mapper.PostResponseMapper
import krasnikov.project.postsapp.feed.post.ui.common.mapper.PostUIMapper
import org.koin.dsl.module

val mapperModule = module {
    factory { PostResponseMapper() }
    factory { PostEntityMapper(get()) }
    factory { PostUIMapper() }
}