package com.example.postsapplication.models



data class CommentItem(
    val body: String,
    val id: Int,
    val name: String,
    val postId: Int
)