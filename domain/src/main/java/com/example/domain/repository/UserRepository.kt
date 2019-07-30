package com.example.domain.repository

import com.example.domain.models.User
import io.reactivex.Single

interface UserRepository {
    fun get(): Single<List<User>>
}