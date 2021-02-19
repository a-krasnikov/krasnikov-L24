package krasnikov.project.postsapp.app.di

import krasnikov.project.postsapp.post.common.data.mapper.PostEntityMapper
import krasnikov.project.postsapp.post.common.data.mapper.PostResponseMapper
import krasnikov.project.postsapp.post.feed.ui.mapper.PostUIMapper
import org.koin.dsl.module

val mapperModule = module {
    factory { PostResponseMapper() }
    factory { PostEntityMapper(get()) }
    factory { PostUIMapper() }
}