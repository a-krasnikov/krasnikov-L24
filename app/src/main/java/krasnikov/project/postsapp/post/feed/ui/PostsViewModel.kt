package krasnikov.project.postsapp.post.feed.ui

import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import krasnikov.project.postsapp.post.common.data.PostRepository
import krasnikov.project.postsapp.post.feed.domain.GetPostsUseCase
import krasnikov.project.postsapp.post.feed.ui.mapper.PostUIMapper
import krasnikov.project.postsapp.post.feed.ui.model.PostUIModel
import krasnikov.project.postsapp.utils.Resource
import kotlin.Exception

class PostsViewModel(
    private val getPostsUseCase: GetPostsUseCase,
    private val postUIMapper: PostUIMapper,
) : ViewModel() {

    private val _content = MediatorLiveData<Resource<List<PostUIModel>>>()

    val content
        get() = _content as LiveData<Resource<List<PostUIModel>>>

    init {
        loadFromDb()
    }

    private fun loadFromDb() {
        _content.value = Resource.Loading
        viewModelScope.launch {
            getPostsUseCase()
                .map { postUIMapper.map(it) }
                .catch { _content.value = Resource.Error(it as Exception) }
                .collect { _content.value = Resource.Content(it) }
        }
    }
}