package krasnikov.project.postsapp.postsfeed.post.data.repository

import krasnikov.project.postsapp.postsfeed.post.data.mapper.PostDomainMapper
import krasnikov.project.postsapp.utils.Result
import krasnikov.project.postsapp.postsfeed.post.data.source.remote.RemotePostDataSource
import krasnikov.project.postsapp.postsfeed.post.domain.model.PostModel
import krasnikov.project.postsapp.utils.AsyncOperation

class PostRepositoryImpl(
    private val remoteDataSource: RemotePostDataSource,
    private val postDomainMapper: PostDomainMapper
) : PostRepository {

    override fun getPosts(): AsyncOperation<Result<List<PostModel>>> {
        return remoteDataSource.getAllPosts().map { postDomainMapper.mapList(it) }
    }
}