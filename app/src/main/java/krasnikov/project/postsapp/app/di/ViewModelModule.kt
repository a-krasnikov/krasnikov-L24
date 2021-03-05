package krasnikov.project.postsapp.app.di

import krasnikov.project.postsapp.post.create.ui.CreatePostViewModel
import krasnikov.project.postsapp.post.feed.ui.PostsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { PostsViewModel(get(), get()) }
    viewModel { CreatePostViewModel(get()) }
}