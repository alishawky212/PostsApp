package com.example.data.repository

import com.example.data.mappers.PostMapper
import com.example.data.remote.RemoteApis
import com.example.domain.models.Post
import com.example.domain.repository.PostsRepository
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostRepositoryImpl @Inject constructor(private val remoteApis: RemoteApis,private val mapper: PostMapper) : PostsRepository {
    override fun getPosts(): Single<List<Post>> {
        return remoteApis.getPosts().map { mapper.mapToDomain(it) }
    }

    override fun getPost(postId: String): Single<Post> {
        return remoteApis.getPost(postId).map { mapper.mapToDomain(it) }
    }
}