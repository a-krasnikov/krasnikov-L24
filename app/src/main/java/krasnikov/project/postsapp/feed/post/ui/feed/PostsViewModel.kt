package krasnikov.project.postsapp.feed.post.ui.feed

import androidx.lifecycle.*
import krasnikov.project.postsapp.feed.post.data.repository.PostRepository
import krasnikov.project.postsapp.feed.post.ui.common.mapper.PostUIMapper
import krasnikov.project.postsapp.feed.post.ui.common.model.PostUIModel
import krasnikov.project.postsapp.utils.CancelableOperation
import krasnikov.project.postsapp.utils.Result

class PostsViewModel(
    private val postRepository: PostRepository,
    private val postUIMapper: PostUIMapper
) : ViewModel() {

    private lateinit var cancelableOperation: CancelableOperation

    private val posts by lazy { MutableLiveData<List<PostUIModel>>() }
    private val loadedSuccess by lazy { MutableLiveData<Boolean>() }

    val postsLiveData: LiveData<List<PostUIModel>>
        get() = posts

    val loadedSuccessLiveData: LiveData<Boolean>
        get() = loadedSuccess

    fun loadData() {
        cancelableOperation = postRepository.getPosts().map { result ->
            result.map { postUIMapper.map(it) }
        }.postOnMainThread {
            when (it) {
                is Result.Success -> {
                    posts.postValue(it.data)
                    loadedSuccess.postValue(true)
                }
                is Result.Error -> {
                    loadedSuccess.postValue(false)
                }
            }
        }
    }

    override fun onCleared() {
        cancelableOperation.cancel()
        super.onCleared()
    }
}