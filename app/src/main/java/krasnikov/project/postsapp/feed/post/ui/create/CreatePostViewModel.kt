package krasnikov.project.postsapp.feed.post.ui.create

import androidx.lifecycle.ViewModel
import krasnikov.project.postsapp.feed.post.data.source.local.entity.PostEntity
import krasnikov.project.postsapp.feed.post.domain.SavePostUseCase
import krasnikov.project.postsapp.utils.Result

class CreatePostViewModel(private val savePostUseCase: SavePostUseCase) : ViewModel() {

    fun savePost(post: PostEntity): Result<Unit> {
        return savePostUseCase.invoke(post)
    }
}