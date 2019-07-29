package com.example.domain.usecase

import com.example.domain.mapper.UserPostMapper
import com.example.domain.repository.PostsRepository
import com.example.domain.repository.UserRepository
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class UserPostUseCase @Inject constructor(private val userRepository: UserRepository,
                                          private val postRepository: PostsRepository,
                                          private val mapper: UserPostMapper
) {

    fun get(userId: String, postId: String): Single<CombinedUserPost> =
        Single.zip(userRepository.get(userId), postRepository.getPost(postId),
            BiFunction { user, post -> mapper.map(user, post) })
}