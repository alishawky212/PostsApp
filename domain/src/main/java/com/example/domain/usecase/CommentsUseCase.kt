package com.example.domain.usecase

import com.example.domain.models.Comment
import com.example.domain.repository.CommentRepository
import io.reactivex.Single
import javax.inject.Inject

class CommentsUseCase @Inject constructor(private val repository: CommentRepository) {
    fun getComments(postId:String): Single<List<Comment>> = repository.get(postId)
}