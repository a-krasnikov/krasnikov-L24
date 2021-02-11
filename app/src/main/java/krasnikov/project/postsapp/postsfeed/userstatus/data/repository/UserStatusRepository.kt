package krasnikov.project.postsapp.postsfeed.userstatus.data.repository

import krasnikov.project.postsapp.postsfeed.post.domain.model.Status

interface UserStatusRepository {
    fun getUserStatusById(id: Long): Status
}