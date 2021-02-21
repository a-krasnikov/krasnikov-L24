package krasnikov.project.postsapp.post.common.data

import androidx.lifecycle.LiveData
import krasnikov.project.postsapp.post.common.data.mapper.PostEntityMapper
import krasnikov.project.postsapp.post.common.data.mapper.PostResponseMapper
import krasnikov.project.postsapp.post.common.data.source.local.LocalPostDataSource
import krasnikov.project.postsapp.post.common.data.model.PostEntity
import krasnikov.project.postsapp.post.common.data.source.remote.RemotePostDataSource
import krasnikov.project.postsapp.post.common.domain.PostModel
import krasnikov.project.postsapp.utils.AsyncOperation
import krasnikov.project.postsapp.utils.Result
import krasnikov.project.postsapp.utils.mapAsync

class PostRepository(
    private val remoteDataSource: RemotePostDataSource,
    private val localDataSource: LocalPostDataSource,
    private val postResponseMapper: PostResponseMapper,
    private val postEntityMapper: PostEntityMapper
) {

    fun observePosts(): LiveData<List<PostModel>> {
        return localDataSource.observePosts()
            .mapAsync { postEntityMapper.map(it) }
    }

    fun savePost(post: PostEntity) {
        localDataSource.savePost(post)
    }

    fun refreshPostsFromRemote(): AsyncOperation<Result<Unit>> {
        return remoteDataSource.getAllPosts().map {
            when (it) {
                is Result.Success -> {
                    localDataSource.refreshRemotePosts(postResponseMapper.map(it.data))
                    return@map Result.Success(Unit)
                }
                is Result.Error -> {
                    return@map it
                }
            }
        }
    }
}