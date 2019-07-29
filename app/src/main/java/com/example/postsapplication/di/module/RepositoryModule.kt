package com.example.postsapplication.di.module

import com.example.data.repository.CommentRepositoryImpl
import com.example.data.repository.PostRepositoryImpl
import com.example.data.repository.UserRepositoryImpl
import com.example.domain.repository.CommentRepository
import com.example.domain.repository.PostsRepository
import com.example.domain.repository.UserRepository
import dagger.Binds
import dagger.Module


@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindPostsRepository(repositoryImpl:PostRepositoryImpl):PostsRepository

    @Binds
    abstract fun bindCommentRepository(repositoryImpl: CommentRepositoryImpl):CommentRepository

    @Binds
    abstract fun bindUserRepository(repositoryImpl: UserRepositoryImpl):UserRepository
}