package com.example.postsapplication.models.mappers

import com.example.domain.models.Comment
import com.example.postsapplication.models.CommentItem
import javax.inject.Inject

class CommentItemMapper @Inject constructor() {

    fun mapToPresentation(commentsList: List<Comment>): List<CommentItem> = commentsList.map {
        CommentItem(body = it.body, id = it.id,name = it.name,postId = it.postId)
    }
}
