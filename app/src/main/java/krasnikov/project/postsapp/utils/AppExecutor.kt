package krasnikov.project.postsapp.utils

import android.os.Handler
import android.os.Looper
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class AppExecutor {
    private val mainHandler by lazy { Handler(Looper.getMainLooper()) }
    private val executor: ExecutorService by lazy {
        Executors.newFixedThreadPool(
            NETWORK_THREAD_COUNT
        )
    }

    fun <T> async(operation: () -> T): AsyncOperation<T> {
        return AsyncOperation(operation, mainHandler, executor)
    }

    companion object {
        private const val NETWORK_THREAD_COUNT = 2
    }
}