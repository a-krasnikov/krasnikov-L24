package krasnikov.project.postsapp.userstatus.data

import krasnikov.project.postsapp.userstatus.data.dao.BannedUserDao
import krasnikov.project.postsapp.userstatus.data.dao.WarningUserDao
import krasnikov.project.postsapp.userstatus.data.entity.BannedUserEntity
import krasnikov.project.postsapp.userstatus.data.entity.WarningUserEntity

class UserStatusRepository(
    private val bannedUserDao: BannedUserDao,
    private val warningUserDao: WarningUserDao,
) {

    private val bannedUsers = hashSetOf<BannedUserEntity>()
    private val warningUsers = hashSetOf<WarningUserEntity>()

    fun getUserStatusById(id: Long): UserStatus {
        if (bannedUsers.isEmpty()) {
            refreshBannedUsers()
        }

        if (warningUsers.isEmpty()) {
            refreshWarningUsers()
        }

        return when {
            bannedUsers.any { it.id == id } -> UserStatus.BANNED
            warningUsers.any { it.id == id } -> UserStatus.WITH_WARNING
            else -> UserStatus.STANDART
        }
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
