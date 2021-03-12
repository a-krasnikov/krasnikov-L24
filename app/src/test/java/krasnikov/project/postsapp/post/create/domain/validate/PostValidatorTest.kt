package krasnikov.project.postsapp.post.create.domain.validate

import android.content.res.Resources
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import krasnikov.project.postsapp.R
import krasnikov.project.postsapp.post.common.data.model.PostEntity
import krasnikov.project.postsapp.post.create.domain.validate.error.ValidationError
import org.junit.Test
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import java.lang.Exception
import java.lang.StringBuilder
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

internal class PostValidatorTest {

    private val resources = mockk<Resources>(relaxed = true) {
        every { getString(R.string.validate_pattern_title) } returns "(?iu)Реклама|Товар|Куплю"
    }

    @TestFactory
    fun `if title length is less than 3 or more than 50 validate throw ValidationError with string resource error_post_title_length_validation`(): List<DynamicTest> {
        return listOf((0..3), (50..100)).map { range ->
            DynamicTest.dynamicTest("if title length in $range throw ValidationError") {
                val postValidator = PostValidator(resources)

                val title = "a".repeat(range.random())

                val exception = assertFailsWith<ValidationError> {
                    postValidator.validate(
                        PostEntity(
                            userId = 1,
                            title = title,
                            body = "Post body",
                            isLocal = true
                        )
                    )
                }

                assertEquals(R.string.error_post_title_length_validation, exception.stringRes)
            }
        }
    }

    @TestFactory
    fun `if title contains forbidden words validate throw ValidationError with string resource error_post_title_content_validation`(): List<DynamicTest> {
        return listOf("Реклама", "Товар", "Куплю").map { forbiddenWord ->
            DynamicTest.dynamicTest("if title length contains $forbiddenWord throw ValidationError") {
                val postValidator = PostValidator(resources)

                val exception = assertFailsWith<ValidationError> {
                    postValidator.validate(
                        PostEntity(
                            userId = 1,
                            title = forbiddenWord,
                            body = "Post body",
                            isLocal = true
                        )
                    )
                }

                assertEquals(R.string.error_post_title_content_validation, exception.stringRes)
            }
        }
    }

    @TestFactory
    fun `if body length is less than 5 or more than 500 validate throw ValidationError with string resource error_post_body_length_validation`(): List<DynamicTest> {
        return listOf((0..5), (500..600)).map { range ->
            DynamicTest.dynamicTest("if body length in $range throw ValidationError") {
                val postValidator = PostValidator(resources)

                val body = "a".repeat(range.random())

                val exception = assertFailsWith<ValidationError> {
                    postValidator.validate(
                        PostEntity(
                            userId = 1,
                            title = "Title",
                            body = body,
                            isLocal = true
                        )
                    )
                }

                assertEquals(R.string.error_post_body_length_validation, exception.stringRes)
            }
        }
    }

    @Test
    fun `if post is valid validate passed`() {
        val postValidator = PostValidator(resources)

        try {
            postValidator.validate(
                PostEntity(
                    userId = 1,
                    title = "Post Title",
                    body = "Post Body",
                    isLocal = true
                )
            )
        } catch (e: Exception) {
            assert(false) { "validate throw Exception ${e::class.java.simpleName}" }
        }

        assert(true)
    }
}