package com.example.data.repository

import com.example.data.mappers.CommentMapper
import com.example.data.remote.RemoteApis
import com.example.domain.models.Comment
import com.example.domain.repository.CommentRepository
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CommentRepositoryImpl @Inject constructor(private val remoteApis: RemoteApis, private val mapper: CommentMapper):CommentRepository{
    override fun get(postId: String): Single<List<Comment>> {
       return remoteApis.getPostComments(postId).map { mapper.mapToDomain(it) }
    }
}