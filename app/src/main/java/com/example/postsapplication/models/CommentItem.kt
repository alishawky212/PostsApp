package com.example.postsapplication.models



data class CommentItem(
    val body: String,
    val id: String,
    val name: String,
    val postId: String,
    val email:String
)