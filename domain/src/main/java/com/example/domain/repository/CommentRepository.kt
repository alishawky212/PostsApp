package com.example.domain.repository

import com.example.domain.models.Comment
import io.reactivex.Single

interface CommentRepository {
    fun get(postId: String): Single<List<Comment>>
}