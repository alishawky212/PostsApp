package com.example.domain.usecase

import com.example.domain.repository.CommentRepository
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when` as _when

class CommentsUseCaseTest {
    private lateinit var usecase: CommentsUseCase

    private val mockRepository = mock<CommentRepository> {}

    private val postId = 1
    private val commentList = listOf(createComment())

    @Before
    fun setUp() {
        usecase = CommentsUseCase(mockRepository)
    }

    @Test
    fun repositoryGet() {
        _when(mockRepository.get(postId = postId.toString())).thenReturn(Single.just(commentList))

        val test = usecase.getComments(postId.toString()).test()

        verify(mockRepository).get(postId.toString())

        test.assertNoErrors()
        test.assertComplete()
        test.assertValueCount(1)
        test.assertValue(commentList)
    }
}