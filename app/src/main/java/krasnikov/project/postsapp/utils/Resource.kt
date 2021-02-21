package krasnikov.project.postsapp.utils

sealed class Resource<out T> {
    object Loading : Resource<Nothing>()
    data class Content<out T>(val data: T) : Resource<T>()
    data class Error(val exception: Exception) : Resource<Nothing>()
}