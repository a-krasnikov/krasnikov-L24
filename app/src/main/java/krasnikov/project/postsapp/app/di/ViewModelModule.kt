package krasnikov.project.postsapp.app.di

import krasnikov.project.postsapp.postsfeed.post.ui.viewmodel.PostsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { PostsViewModel(get(), get()) }
}