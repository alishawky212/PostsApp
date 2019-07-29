package com.example.postsapplication.models

sealed class PostsState <out T>{
        object LoadingState : PostsState<Nothing>()
        data class DataState<out T>(val data: List<T>) : PostsState<T>()
        data class ErrorState<out T>(val data: String) : PostsState<T>()
}