package krasnikov.project.postsapp.post.feed.ui

import androidx.lifecycle.*
import krasnikov.project.postsapp.post.common.data.PostRepository
import krasnikov.project.postsapp.post.feed.domain.SortPostsUseCase
import krasnikov.project.postsapp.post.feed.ui.mapper.PostUIMapper
import krasnikov.project.postsapp.post.feed.ui.model.PostUIModel
import krasnikov.project.postsapp.utils.CancelableOperation
import krasnikov.project.postsapp.utils.Resource
import krasnikov.project.postsapp.utils.Result

class PostsViewModel(
    private val postRepository: PostRepository,
    private val postUIMapper: PostUIMapper,
    private val sortPostsUseCase: SortPostsUseCase
) : ViewModel() {

    private val _content = MediatorLiveData<Resource<List<PostUIModel>>>()

    val content
        get() = _content as LiveData<Resource<List<PostUIModel>>>

    private lateinit var cancelableOperation: CancelableOperation

    init {
        loadFromDb()
        refreshData()
    }

    private fun loadFromDb() {
        _content.addSource(postRepository.observePosts()) {
            _content.value = Resource.Loading
            _content.value = Resource.Content(postUIMapper.map(sortPostsUseCase(it)))
        }
    }

    fun refreshData() {
        _content.value = Resource.Loading
        cancelableOperation = postRepository.refreshPostsFromRemote().postOnMainThread {
            if (it is Result.Error) {
                _content.value = Resource.Error(it.exception)
            }
        }
    }

    override fun onCleared() {
        cancelableOperation.cancel()
        super.onCleared()
    }
}