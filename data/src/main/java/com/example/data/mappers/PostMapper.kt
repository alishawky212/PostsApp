package com.example.data.mappers

import com.example.data.remotemodels.RemotePost
import com.example.domain.models.Post
import javax.inject.Inject

class PostMapper @Inject constructor() {

    fun mapToDomain(entity: RemotePost): Post = Post(entity.body,
        entity.id,
        entity.title,
        entity.userId)

    fun mapToDomain(list: List<RemotePost>): List<Post> = list.map { mapToDomain(it) }
}