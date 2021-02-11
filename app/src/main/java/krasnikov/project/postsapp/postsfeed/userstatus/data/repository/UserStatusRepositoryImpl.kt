package krasnikov.project.postsapp.postsfeed.userstatus.data.repository

import krasnikov.project.postsapp.postsfeed.post.domain.model.Status
import krasnikov.project.postsapp.postsfeed.userstatus.data.source.local.LocalUserStatusDataSource

class UserStatusRepositoryImpl(
    private val localDataSource: LocalUserStatusDataSource
) : UserStatusRepository {

    override fun getUserStatusById(id: Long): Status {
        return localDataSource.getUserStatusById(id)
    }
}