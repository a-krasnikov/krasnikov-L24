package krasnikov.project.postsapp.post.common.data

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import krasnikov.project.postsapp.post.common.data.mapper.PostEntityMapper
import krasnikov.project.postsapp.post.common.data.mapper.PostResponseMapper
import krasnikov.project.postsapp.post.common.data.source.local.LocalPostDataSource
import krasnikov.project.postsapp.post.common.data.model.PostEntity
import krasnikov.project.postsapp.post.common.data.source.remote.RemotePostDataSource
import krasnikov.project.postsapp.post.common.domain.PostModel

class PostRepository(
    private val remoteDataSource: RemotePostDataSource,
    private val localDataSource: LocalPostDataSource,
    private val postResponseMapper: PostResponseMapper,
    private val postEntityMapper: PostEntityMapper,
    private val ioDispatcher: CoroutineDispatcher,
) {

    suspend fun observePosts(): Flow<List<PostModel>> {
        refreshPostsFromRemote()
        return localDataSource.observePosts().map {
            postEntityMapper.map(it)
        }.flowOn(ioDispatcher)
    }

    suspend fun savePost(post: PostEntity) {
        withContext(ioDispatcher) {
            localDataSource.savePost(post)
        }
    }

    suspend fun refreshPostsFromRemote() {
        withContext(ioDispatcher) {
            localDataSource.refreshRemotePosts(postResponseMapper.map(remoteDataSource.getAllPosts()))
        }
    }
}