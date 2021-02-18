package krasnikov.project.postsapp.feed.userstatus.data.repository

import krasnikov.project.postsapp.feed.post.domain.model.Status
import krasnikov.project.postsapp.feed.userstatus.data.dao.BannedUserDao
import krasnikov.project.postsapp.feed.userstatus.data.dao.WarningUserDao
import krasnikov.project.postsapp.feed.userstatus.data.entity.BannedUserEntity
import krasnikov.project.postsapp.feed.userstatus.data.entity.WarningUserEntity

class UserStatusRepository(
    private val bannedUserDao: BannedUserDao,
    private val warningUserDao: WarningUserDao,
) {

    private val bannedUsers = hashSetOf<BannedUserEntity>()
    private val warningUsers = hashSetOf<WarningUserEntity>()

    fun getUserStatusById(id: Long): Status {
        if (bannedUsers.isEmpty()) {
            refreshBannedUsers()
        }

        if (warningUsers.isEmpty()) {
            refreshWarningUsers()
        }

        bannedUsers.find { it.id == id }?.let {
            return Status.BANNED
        }
        warningUsers.find { it.id == id }?.let {
            return Status.WITH_WARNING
        }

        return Status.STANDART
    }

    private fun refreshBannedUsers() {
        bannedUsers.clear()
        bannedUsers.addAll(bannedUserDao.getAll())
    }

    private fun refreshWarningUsers() {
        warningUsers.clear()
        warningUsers.addAll(warningUserDao.getAll())
    }
}