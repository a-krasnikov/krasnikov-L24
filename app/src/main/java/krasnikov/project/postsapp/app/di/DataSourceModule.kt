package krasnikov.project.postsapp.app.di

import krasnikov.project.postsapp.postsfeed.post.data.source.remote.RemotePostDataSource
import krasnikov.project.postsapp.postsfeed.post.data.source.remote.RemotePostDataSourceImpl
import krasnikov.project.postsapp.postsfeed.userstatus.data.source.local.LocalUserStatusDataSource
import krasnikov.project.postsapp.postsfeed.userstatus.data.source.local.LocalUserStatusDataSourceImpl
import org.koin.dsl.module

val dataSourceModule = module {
    single<RemotePostDataSource> { RemotePostDataSourceImpl(get(), get()) }
    single<LocalUserStatusDataSource> { LocalUserStatusDataSourceImpl() }
}