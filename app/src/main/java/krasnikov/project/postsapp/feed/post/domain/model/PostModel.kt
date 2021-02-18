package krasnikov.project.postsapp.feed.post.domain.model

data class PostModel(
    val id: Long,
    val userId: Long,
    val title: String,
    val body: String,
    val isLocal: Boolean,
    val status: Status,
)

enum class Status {
    STANDART, BANNED, WITH_WARNING
}