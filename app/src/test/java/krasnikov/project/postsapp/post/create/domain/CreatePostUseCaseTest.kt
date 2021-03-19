package krasnikov.project.postsapp.post.create.domain

import io.mockk.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import krasnikov.project.postsapp.R
import krasnikov.project.postsapp.post.common.data.PostRepository
import krasnikov.project.postsapp.post.common.data.model.PostEntity
import krasnikov.project.postsapp.post.create.domain.validate.PostValidator
import krasnikov.project.postsapp.post.create.domain.validate.error.ValidationError
import org.junit.After
import org.junit.Before
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

@ExperimentalCoroutinesApi
internal class CreatePostUseCaseTest {

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
    fun `if validation passed then post is created`() = runBlockingTest {
        val repository = mockk<PostRepository>(relaxUnitFun = true)
        val postValidator = mockk<PostValidator>(relaxUnitFun = true)
        val post = mockk<PostEntity>()

        val createPostUseCase = CreatePostUseCase(repository, postValidator, testDispatcher)
        createPostUseCase.invoke(post)

        coVerifySequence {
            postValidator.validate(post)
            repository.savePost(post)
        }
    }

    @Test
    fun `if validation fail then post is not created`() = runBlockingTest {
        val repository = mockk<PostRepository>(relaxUnitFun = true)
        val postValidator = mockk<PostValidator>() {
            every { validate(any()) } throws ValidationError(R.string.error_post_body_length_validation)
        }
        val post = mockk<PostEntity>()

        val createPostUseCase = CreatePostUseCase(repository, postValidator, testDispatcher)

        assertFailsWith<ValidationError> { createPostUseCase.invoke(post) }

        verify { postValidator.validate(post) }
        coVerify(inverse = true) { repository.savePost(post) }
    }
}