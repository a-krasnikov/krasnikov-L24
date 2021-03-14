package krasnikov.project.postsapp.post.feed.domain

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import krasnikov.project.postsapp.post.common.data.PostRepository
import krasnikov.project.postsapp.post.common.domain.PostModel
import krasnikov.project.postsapp.userstatus.data.UserStatus
import org.junit.After
import org.junit.Before
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

@ExperimentalCoroutinesApi
internal class GetPostsUseCaseTest {

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `local posts must be first and sorted desc by Id`() = runBlockingTest {
        val localPost1 = PostModel(
            id = 1,
            userId = 1,
            title = "title",
            body = "body",
            isLocal = true,
            status = UserStatus.STANDART
        )
        val localPost2 = PostModel(
            id = 2,
            userId = 1,
            title = "title",
            body = "body",
            isLocal = true,
            status = UserStatus.STANDART
        )
        val remotePost = PostModel(
            id = 3,
            userId = 1,
            title = "title",
            body = "body",
            isLocal = false,
            status = UserStatus.STANDART
        )

        val expectedPosts = listOf(localPost2, localPost1, remotePost)

        val repository = mockk<PostRepository>(relaxUnitFun = true) {
            coEvery { observePosts() } returns flowOf(listOf(remotePost, localPost1, localPost2))
        }

        val getPostUseCase = GetPostsUseCase(repository, testDispatcher)
        val actualPosts = getPostUseCase.invoke().first()

        assertIterableEquals(expectedPosts, actualPosts)
    }
}
