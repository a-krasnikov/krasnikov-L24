package krasnikov.project.postsapp.app.di

import krasnikov.project.postsapp.feed.post.ui.create.CreatePostViewModel
import krasnikov.project.postsapp.feed.post.ui.feed.PostsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { PostsViewModel(get(), get(), get()) }
    viewModel { CreatePostViewModel(get()) }
}