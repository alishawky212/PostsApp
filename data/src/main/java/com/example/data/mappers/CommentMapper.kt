package com.example.data.mappers

import com.example.data.remotemodels.RemoteComment
import com.example.domain.models.Comment
import javax.inject.Inject

class CommentMapper @Inject constructor() {

    fun mapToDomain(entity: RemoteComment): Comment = Comment(entity.body,
        entity.email,
        entity.id,
        entity.name,
        entity.postId)

    fun mapToDomain(list: List<RemoteComment>): List<Comment> = list.map { mapToDomain(it) }
}