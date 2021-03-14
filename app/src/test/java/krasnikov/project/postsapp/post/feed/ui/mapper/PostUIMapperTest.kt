package krasnikov.project.postsapp.post.feed.ui.mapper

import android.graphics.Color
import krasnikov.project.postsapp.post.common.domain.PostModel
import krasnikov.project.postsapp.post.feed.ui.model.PostUIModel
import krasnikov.project.postsapp.userstatus.data.UserStatus
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class PostUIMapperTest {

    @Test
    fun `if user status standart`() {
        val expectedResult = PostUIModel.Post(
            id = 1,
            userId = 1,
            title = "title",
            body = "body",
            backgroundColor = Color.TRANSPARENT,
            isVisibleWarning = false
        )

        val postUIMapper = PostUIMapper()
        val actualResult = postUIMapper.map(
            PostModel(
                id = 1,
                userId = 1,
                title = "title",
                body = "body",
                isLocal = true,
                status = UserStatus.STANDART
            )
        )

        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `if user status with warning`() {
        val expectedResult = PostUIModel.Post(
            id = 1,
            userId = 1,
            title = "title",
            body = "body",
            backgroundColor = Color.RED,
            isVisibleWarning = true
        )

        val postUIMapper = PostUIMapper()
        val actualResult = postUIMapper.map(
            PostModel(
                id = 1,
                userId = 1,
                title = "title",
                body = "body",
                isLocal = true,
                status = UserStatus.WITH_WARNING
            )
        )

        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `if user status banned`() {
        val expectedResult = PostUIModel.BannedPost(id = 1, userId = 1)

        val postUIMapper = PostUIMapper()
        val actualResult = postUIMapper.map(
            PostModel(
                id = 1,
                userId = 1,
                title = "title",
                body = "body",
                isLocal = true,
                status = UserStatus.BANNED
            )
        )

        assertEquals(expectedResult, actualResult)
    }
}
