package com.example.data.repository

import com.example.data.createComment
import com.example.data.mappers.CommentMapper
import com.example.data.remote.RemoteApis
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when` as _when

class CommentRepositoryImplTest {
    private lateinit var repository: CommentRepositoryImpl

    private val mockApi = mock<RemoteApis> {}
    private val mapper = CommentMapper()

    private val postId = "1"

    private val remoteItem = createComment()

    private val remoteList = listOf(remoteItem)

    @Before
    fun setUp() {
        repository = CommentRepositoryImpl(remoteApis = mockApi, mapper = mapper)
    }

    @Test
    fun get() {
        _when(mockApi.getPostComments(postId)).thenReturn(Single.just(remoteList))

        val test = repository.get(postId).test()

        verify(mockApi).getPostComments(postId)
        test.assertValue(mapper.mapToDomain(remoteList))
    }
}