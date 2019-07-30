package com.example.domain.usecase

import com.example.domain.mapper.UserPostMapper
import com.example.domain.repository.PostsRepository
import com.example.domain.repository.UserRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when` as _when


class UsersPostsUseCaseTest {

    private lateinit var useCase: UsersPostsUseCase

    private val mockUserRepository = mock<UserRepository> {}

    private val mockPostsRepository = mock<PostsRepository> {}

    private val mapper = UserPostMapper()

    private val userList = listOf(createUser())
    private val postList = listOf(createPost())

    @Before
    fun setUp() {
        useCase = UsersPostsUseCase(mockPostsRepository,mockUserRepository,mapper)
    }

    @Test
    fun get() {
        _when(mockUserRepository.get()).thenReturn(Single.just(userList))
        _when(mockPostsRepository.getPosts()).thenReturn(Single.just(postList))

        val test = useCase.get().test()

        verify(mockUserRepository).get()
        verify(mockPostsRepository).getPosts()

        test.assertNoErrors()
        test.assertComplete()
        test.assertValueCount(1)
        test.assertValue(mapper.map(userList, postList))
    }
}