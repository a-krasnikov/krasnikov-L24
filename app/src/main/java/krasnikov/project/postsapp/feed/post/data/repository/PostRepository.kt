package krasnikov.project.postsapp.feed.post.data.repository

import krasnikov.project.postsapp.feed.post.data.mapper.PostEntityMapper
import krasnikov.project.postsapp.feed.post.data.mapper.PostResponseMapper
import krasnikov.project.postsapp.feed.post.data.source.local.LocalPostDataSource
import krasnikov.project.postsapp.feed.post.data.source.remote.RemotePostDataSource
import krasnikov.project.postsapp.utils.Result
import krasnikov.project.postsapp.feed.post.domain.model.PostModel
import krasnikov.project.postsapp.utils.AsyncOperation

class PostRepository(
    private val remoteDataSource: RemotePostDataSource,
    private val localDataSource: LocalPostDataSource,
    private val postResponseMapper: PostResponseMapper,
    private val postEntityMapper: PostEntityMapper
) {

    fun getPosts(): AsyncOperation<Result<List<PostModel>>> {
        return localDataSource.getPosts()
            .map { result -> result.map { postEntityMapper.mapToPostModel(it) } }
    }

    fun savePost(post: PostModel) {
        localDataSource.savePost(postEntityMapper.mapFromPostModel(post))
    }

    fun refreshPosts(): AsyncOperation<Result<Unit>> {
        return updatePostsFromRemote()
    }

    private fun updatePostsFromRemote(): AsyncOperation<Result<Unit>> {
        return remoteDataSource.getAllPosts().map {
            when (it) {
                is Result.Success -> {
                    localDataSource.deleteAllPosts()
                    localDataSource.savePosts(postResponseMapper.map(it.data))
                    return@map Result.Success(Unit)
                }
                is Result.Error -> {
                    return@map it
                }
            }
        }
    }
}