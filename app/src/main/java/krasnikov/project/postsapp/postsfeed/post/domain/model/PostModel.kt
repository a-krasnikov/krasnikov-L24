package krasnikov.project.postsapp.postsfeed.post.domain.model

data class PostModel(
    val id: Long,
    val userId: Long,
    val title: String,
    val body: String,
    val status: Status,
)

enum class Status {
    STANDART, BANNED, WITH_WARNING
}