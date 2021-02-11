package krasnikov.project.postsapp.postsfeed.userstatus.data.source.local

import krasnikov.project.postsapp.postsfeed.post.domain.model.Status

class LocalUserStatusDataSourceImpl : LocalUserStatusDataSource {

    private val bannedUserIds = hashSetOf<Long>(7)
    private val warningUserIds = hashSetOf<Long>(3, 4)

    override fun getUserStatusById(id: Long): Status {
        return when {
            bannedUserIds.contains(id) -> {
                Status.BANNED
            }
            warningUserIds.contains(id) -> {
                Status.WITH_WARNING
            }
            else -> Status.STANDART
        }
    }
}