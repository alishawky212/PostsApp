package com.example.domain.usecase

import com.example.domain.mapper.UserPostMapper
import com.example.domain.models.Post
import com.example.domain.models.User
import com.example.domain.repository.PostsRepository
import com.example.domain.repository.UserRepository
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import javax.inject.Inject

data class CombinedUserPost(val user: User, val post: Post)

class UsersPostsUseCase @Inject constructor(
    private val postRepository: PostsRepository,
    private val userRepository: UserRepository,
    private val userPostMapper: UserPostMapper
) {

    fun get(): Single<List<CombinedUserPost>> = Single.zip(userRepository.get(), postRepository.getPosts(),
        BiFunction { userList, postList -> userPostMapper.map(userList, postList) })
}