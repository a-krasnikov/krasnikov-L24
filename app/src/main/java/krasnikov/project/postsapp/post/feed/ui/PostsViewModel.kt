package krasnikov.project.postsapp.post.feed.ui

import androidx.lifecycle.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import krasnikov.project.postsapp.post.common.data.PostRepository
import krasnikov.project.postsapp.post.feed.domain.SortPostsUseCase
import krasnikov.project.postsapp.post.feed.ui.mapper.PostUIMapper
import krasnikov.project.postsapp.post.feed.ui.model.PostUIModel
import krasnikov.project.postsapp.utils.Resource
import java.lang.Exception

class PostsViewModel(
    private val postRepository: PostRepository,
    private val postUIMapper: PostUIMapper,
    private val sortPostsUseCase: SortPostsUseCase
) : ViewModel() {

    private val _content = MediatorLiveData<Resource<List<PostUIModel>>>()

    val content
        get() = _content as LiveData<Resource<List<PostUIModel>>>

    private val compositeDisposable = CompositeDisposable()

    init {
        loadFromDb()
        refreshData()
    }

    private fun loadFromDb() {
        compositeDisposable.add(
            postRepository.observePosts().map { postUIMapper.map(sortPostsUseCase(it)) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ userList ->
                    _content.value = Resource.Content(userList)
                }, { throwable ->
                    _content.value = Resource.Error(Exception(throwable))
                })
        )
    }

    fun refreshData() {
        _content.value = Resource.Loading
        compositeDisposable.add(
            postRepository.refreshPostsFromRemote()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({}, { throwable ->
                    _content.value = Resource.Error(Exception(throwable))
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}