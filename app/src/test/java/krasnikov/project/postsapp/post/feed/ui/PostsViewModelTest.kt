package krasnikov.project.postsapp.post.feed.ui

import androidx.lifecycle.Observer
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.test.*
import krasnikov.project.postsapp.CoroutinesTestExtension
import krasnikov.project.postsapp.InstantExecutorExtension
import krasnikov.project.postsapp.post.common.domain.PostModel
import krasnikov.project.postsapp.post.feed.domain.GetPostsUseCase
import krasnikov.project.postsapp.post.feed.ui.mapper.PostUIMapper
import krasnikov.project.postsapp.post.feed.ui.model.PostUIModel
import krasnikov.project.postsapp.userstatus.data.UserStatus
import krasnikov.project.postsapp.utils.Resource
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.*
import java.lang.Exception

@ExperimentalCoroutinesApi
@ExtendWith(InstantExecutorExtension::class)
internal class PostsViewModelTest {

    @JvmField
    @RegisterExtension
    val coroutinesTestExtension = CoroutinesTestExtension()

    @Test
    fun `if result success hide loading and show content`() =
        coroutinesTestExtension.runBlockingTest {
            val posts = listOf(
                PostModel(
                    id = 1,
                    userId = 1,
                    title = "title",
                    body = "body",
                    isLocal = true,
                    status = UserStatus.STANDART
                )
            )

            val expectedResult = listOf(
                PostUIModel.Post(
                    id = 1,
                    userId = 1,
                    title = "title",
                    body = "body",
                    backgroundColor = 1,
                    isVisibleWarning = false
                )
            )

            val getPostsUseCase = mockk<GetPostsUseCase> {
                coEvery { this@mockk() } returns flowOf(posts).onStart { delay(1000) }
            }
            val postUIMapper = mockk<PostUIMapper> {
                every { map(posts) } returns expectedResult
            }
            val observer = mockk<Observer<Resource<List<PostUIModel>>>>(relaxed = true)

            val viewModel = PostsViewModel(getPostsUseCase, postUIMapper)
            viewModel.content.observeForever(observer)

            delay(2000)

            verifySequence {
                observer.onChanged(Resource.Loading)
                observer.onChanged(Resource.Content(expectedResult))
            }
        }

    @Test
    fun `if result failed hide loading and show error`() = coroutinesTestExtension.runBlockingTest {

        val expectedException = Exception("Test Exception")

        val getPostsUseCase = mockk<GetPostsUseCase> {
            coEvery { this@mockk() } returns flow<List<PostModel>> { throw expectedException }.onStart {
                delay(1000)
            }
        }
        val postUIMapper = mockk<PostUIMapper>(relaxed = true)
        val observer = mockk<Observer<Resource<List<PostUIModel>>>>(relaxed = true)

        val viewModel = PostsViewModel(getPostsUseCase, postUIMapper)
        viewModel.content.observeForever(observer)

        delay(2000)

        verifySequence {
            observer.onChanged(Resource.Loading)
            observer.onChanged(Resource.Error(expectedException))
        }
    }
}
