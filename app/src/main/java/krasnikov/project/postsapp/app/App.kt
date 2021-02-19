package krasnikov.project.postsapp.app

import android.app.Application
import krasnikov.project.postsapp.app.di.*
import org.koin.android.ext.koin.androidContext

class App : Application() {

    companion object {
        const val userId: Long = 3
    }

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