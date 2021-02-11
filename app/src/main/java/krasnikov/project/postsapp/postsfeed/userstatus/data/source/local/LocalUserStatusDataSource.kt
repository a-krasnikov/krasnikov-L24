package krasnikov.project.postsapp.postsfeed.userstatus.data.source.local

import krasnikov.project.postsapp.postsfeed.post.domain.model.Status

interface LocalUserStatusDataSource {
    fun getUserStatusById(id: Long): Status
}