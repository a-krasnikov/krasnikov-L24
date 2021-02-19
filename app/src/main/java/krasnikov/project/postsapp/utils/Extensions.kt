package krasnikov.project.postsapp.utils

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

fun View.visibleOrGone(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

fun <T, R> LiveData<T>.mapAsync(transform: (T) -> R): LiveData<R> {
    val result = MediatorLiveData<R>()
    result.addSource(this) {
        Thread { result.postValue(transform(it)) }.start()
    }
    return result
}
