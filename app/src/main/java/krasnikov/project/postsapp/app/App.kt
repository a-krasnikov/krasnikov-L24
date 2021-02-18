package krasnikov.project.postsapp.app

import android.app.Application
import krasnikov.project.postsapp.app.di.*
import org.koin.android.ext.koin.androidContext

class App : Application() {

    companion object {
        lateinit var instance: App
            private set

        const val userId: Long = 2
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin()
    }

    private fun startKoin() {
        org.koin.core.context.startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    multithreadingModule,
                    networkModule,
                    dbModule,
                    dataSourceModule,
                    mapperModule,
                    repositoryModule,
                    viewModelModule,
                    useCaseModule
                )
            )
        }
    }
}