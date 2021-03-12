package krasnikov.project.postsapp.userstatus.data

import io.mockk.every
import io.mockk.mockk
import krasnikov.project.postsapp.userstatus.data.dao.BannedUserDao
import krasnikov.project.postsapp.userstatus.data.dao.WarningUserDao
import krasnikov.project.postsapp.userstatus.data.entity.BannedUserEntity
import krasnikov.project.postsapp.userstatus.data.entity.WarningUserEntity
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class UserStatusRepositoryTest {

    @Test
    fun `if user has ban then getUserStatusById should return BANNED`() {
        val expectedStatus = UserStatus.BANNED

        val bannedUserDao = mockk<BannedUserDao> {
            every { getAll() } returns listOf(BannedUserEntity(2))
        }
        val warningUserDao = mockk<WarningUserDao> {
            every { getAll() } returns listOf(WarningUserEntity(1))
        }

        val userStatusRepository = UserStatusRepository(bannedUserDao, warningUserDao)
        val actualStatus = userStatusRepository.getUserStatusById(2)

        assertEquals(expectedStatus, actualStatus)
    }

    @Test
    fun `if user with warning then getUserStatusById should return WITH_WARNING`() {
        val expectedStatus = UserStatus.WITH_WARNING

        val bannedUserDao = mockk<BannedUserDao> {
            every { getAll() } returns listOf(BannedUserEntity(2))
        }
        val warningUserDao = mockk<WarningUserDao> {
            every { getAll() } returns listOf(WarningUserEntity(1))
        }

        val userStatusRepository = UserStatusRepository(bannedUserDao, warningUserDao)
        val actualStatus = userStatusRepository.getUserStatusById(1)

        assertEquals(expectedStatus, actualStatus)
    }

    @Test
    fun `if user without warning and hasn't ban then getUserStatusById should return STANDART`() {
        val expectedStatus = UserStatus.STANDART

        val bannedUserDao = mockk<BannedUserDao> {
            every { getAll() } returns listOf(BannedUserEntity(2))
        }
        val warningUserDao = mockk<WarningUserDao> {
            every { getAll() } returns listOf(WarningUserEntity(1))
        }

        val userStatusRepository = UserStatusRepository(bannedUserDao, warningUserDao)
        val actualStatus = userStatusRepository.getUserStatusById(3)

        assertEquals(expectedStatus, actualStatus)
    }
}