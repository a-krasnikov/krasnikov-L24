package krasnikov.project.postsapp.app.di

import krasnikov.project.postsapp.post.common.data.source.local.LocalPostDataSource
import krasnikov.project.postsapp.post.common.data.source.remote.RemotePostDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    single { RemotePostDataSource(get()) }
    single { LocalPostDataSource(get()) }
}
