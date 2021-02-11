package krasnikov.project.postsapp.app

import android.app.Application
import krasnikov.project.postsapp.app.di.*
import org.koin.android.ext.koin.androidContext

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin()
    }

    private fun startKoin() {
        org.koin.core.context.startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    multithreadingModule,
                    networkModule,
                    dataSourceModule,
                    mapperModule,
                    repositoryModule,
                    viewModelModule,
                )
            )
        }
    }
}