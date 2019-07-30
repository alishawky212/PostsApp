package com.example.domain.repository

import com.example.domain.models.Post
import io.reactivex.Single

interface PostsRepository {
    fun getPosts(): Single<List<Post>>

}