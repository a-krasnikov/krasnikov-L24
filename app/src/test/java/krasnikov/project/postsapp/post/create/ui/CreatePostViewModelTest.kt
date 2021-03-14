package krasnikov.project.postsapp.post.create.ui

import androidx.lifecycle.Observer
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import krasnikov.project.postsapp.CoroutinesTestExtension
import krasnikov.project.postsapp.InstantExecutorExtension
import krasnikov.project.postsapp.post.common.data.model.PostEntity
import krasnikov.project.postsapp.post.create.domain.CreatePostUseCase
import krasnikov.project.postsapp.utils.Resource
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.RegisterExtension
import java.lang.Exception

@ExperimentalCoroutinesApi
@ExtendWith(InstantExecutorExtension::class)
internal class CreatePostViewModelTest {

    @JvmField
    @RegisterExtension
    val coroutinesTestExtension = CoroutinesTestExtension()

    @Test
    fun `if post create failed show error`() =
        coroutinesTestExtension.runBlockingTest {
            val expectedException = Exception("Test Exception")

            val createPostUseCase = mockk<CreatePostUseCase> {
                coEvery { this@mockk(any()) } throws expectedException
            }
            val observer = mockk<Observer<Resource<Unit>>>(relaxed = true)

            val viewModel = CreatePostViewModel(createPostUseCase)
            viewModel.content.observeForever(observer)
            viewModel.createPost(
                PostEntity(
                    id = 1,
                    userId = 1,
                    title = "Title",
                    body = "Body",
                    isLocal = true
                )
            )

            verify {
                observer.onChanged(Resource.Error(expectedException))
            }
        }
}
