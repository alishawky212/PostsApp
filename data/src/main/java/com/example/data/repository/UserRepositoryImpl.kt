package com.example.data.repository

import com.example.data.mappers.UserMapper
import com.example.data.remote.RemoteApis
import com.example.domain.models.User
import com.example.domain.repository.UserRepository
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(private val remoteApis: RemoteApis, private val mapper: UserMapper) : UserRepository {
    override fun get(): Single<List<User>> {
        return remoteApis.getUsers().map { mapper.mapToDomain(it) }
    }
}