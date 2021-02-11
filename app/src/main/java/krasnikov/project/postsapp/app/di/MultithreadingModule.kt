package krasnikov.project.postsapp.app.di

import krasnikov.project.postsapp.utils.AppMultithreading
import org.koin.dsl.module

val multithreadingModule = module {
    single { AppMultithreading() }
}