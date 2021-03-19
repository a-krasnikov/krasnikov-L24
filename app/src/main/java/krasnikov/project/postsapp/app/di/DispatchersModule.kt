package krasnikov.project.postsapp.app.di

import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dispatchersModule = module {
    single(named(Dispatcher.MAIN)) { Dispatchers.Main }
    single(named(Dispatcher.IO)) { Dispatchers.IO }
    single(named(Dispatcher.DEFAULT)) { Dispatchers.Default }
}

enum class Dispatcher {
    MAIN, IO, DEFAULT
}
