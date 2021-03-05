package krasnikov.project.postsapp.post.create.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import krasnikov.project.postsapp.post.common.data.model.PostEntity
import krasnikov.project.postsapp.post.create.domain.CreatePostUseCase
import krasnikov.project.postsapp.utils.Resource
import java.lang.Exception

class CreatePostViewModel(private val createPostUseCase: CreatePostUseCase) : ViewModel() {

    private val _content = MediatorLiveData<Resource<Unit>>()

    val content
        get() = _content as LiveData<Resource<Unit>>

    private val handlerError = CoroutineExceptionHandler { _, exception ->
        _content.value = Resource.Error(exception as Exception)
    }

    fun createPost(post: PostEntity) {
        viewModelScope.launch(handlerError) {
            createPostUseCase(post)
            _content.value = Resource.Content(Unit)
        }
    }
}