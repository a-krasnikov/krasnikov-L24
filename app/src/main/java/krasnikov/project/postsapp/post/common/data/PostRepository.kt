package krasnikov.project.postsapp.post.common.data

import io.reactivex.Completable
import io.reactivex.Flowable
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
    private val postEntityMapper: PostEntityMapper
) {

    fun observePosts(): Flowable<List<PostModel>> {
        return localDataSource.observePosts().map {
            postEntityMapper.map(it)
        }
    }

    fun savePost(post: PostEntity): Completable {
        return localDataSource.savePost(post)
    }

    fun refreshPostsFromRemote(): Completable {
        return remoteDataSource.getAllPosts().flatMapCompletable {
            localDataSource.refreshRemotePosts(postResponseMapper.map(it))
        }
    }
}