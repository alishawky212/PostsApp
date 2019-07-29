package com.example.domain.usecase

import com.example.domain.models.User
import com.example.domain.repository.UserRepository
import io.reactivex.Single
import javax.inject.Inject

class UserUseCase @Inject constructor(private val repository: UserRepository) {
    fun get(userId: String): Single<User> = repository.get(userId)
}