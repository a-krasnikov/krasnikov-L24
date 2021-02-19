package krasnikov.project.postsapp.post.feed.ui

import androidx.lifecycle.*
import krasnikov.project.postsapp.post.common.data.repository.PostRepository
import krasnikov.project.postsapp.post.feed.domain.SortPostsUseCase
import krasnikov.project.postsapp.post.feed.ui.mapper.PostUIMapper
import krasnikov.project.postsapp.post.feed.ui.model.PostUIModel
import krasnikov.project.postsapp.utils.CancelableOperation
import krasnikov.project.postsapp.utils.Result
import krasnikov.project.postsapp.utils.mapAsync

class PostsViewModel(
    private val postRepository: PostRepository,
    private val postUIMapper: PostUIMapper,
    private val sortPostsUseCase: SortPostsUseCase
) : ViewModel() {

    init {
        loadPostsFromRemote()
    }

    val posts: LiveData<Result<List<PostUIModel>>> by lazy {
        postRepository.observePosts().mapAsync { result ->
            result.map {
                postUIMapper.map(
                    sortPostsUseCase(it)
                )
            }
        }
    }

    private lateinit var cancelableOperation: CancelableOperation

    private val _errorLoadFromRemote by lazy { MutableLiveData(false) }
    val errorLoadFromRemote
        get() = _errorLoadFromRemote as LiveData<Boolean>

    fun loadPostsFromRemote() {
        cancelableOperation = postRepository.refreshPostsFromRemote().postOnMainThread {
            when (it) {
                is Result.Success -> {
                    _errorLoadFromRemote.postValue(false)
                }
                is Result.Error -> {
                    _errorLoadFromRemote.postValue(true)
                }
            }
        }
    }

    override fun onCleared() {
        cancelableOperation.cancel()
        super.onCleared()
    }
}