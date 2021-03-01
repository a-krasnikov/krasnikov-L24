package krasnikov.project.postsapp.post.create.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import krasnikov.project.postsapp.post.common.data.model.PostEntity
import krasnikov.project.postsapp.post.create.domain.CreatePostUseCase
import krasnikov.project.postsapp.utils.Resource
import java.lang.Exception

class CreatePostViewModel(private val createPostUseCase: CreatePostUseCase) : ViewModel() {

    private val _content = MediatorLiveData<Resource<Unit>>()

    val content
        get() = _content as LiveData<Resource<Unit>>

    private val compositeDisposable = CompositeDisposable()

    fun createPost(post: PostEntity) {
        compositeDisposable.add(
            createPostUseCase(post)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _content.value = Resource.Content(Unit)
                }, { throwable ->
                    _content.value = Resource.Error(Exception(throwable))
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}