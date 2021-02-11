package krasnikov.project.postsapp.app.di

import krasnikov.project.postsapp.postsfeed.post.data.mapper.PostDomainMapper
import krasnikov.project.postsapp.postsfeed.post.ui.mapper.PostUIMapper
import org.koin.dsl.module

val mapperModule = module {
    factory { PostDomainMapper(get()) }
    factory { PostUIMapper() }
}