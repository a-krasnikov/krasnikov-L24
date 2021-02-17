package krasnikov.project.postsapp.app.di

import krasnikov.project.postsapp.feed.post.data.source.local.LocalPostDataSource
import krasnikov.project.postsapp.feed.post.data.source.remote.RemotePostDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    single { RemotePostDataSource(get(), get()) }
    single { LocalPostDataSource(get(), get()) }
}