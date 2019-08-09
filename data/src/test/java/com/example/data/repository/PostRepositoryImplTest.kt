package com.example.data.repository

import com.example.data.createPost
import com.example.data.mappers.PostMapper
import com.example.data.remote.RemoteApis
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when` as _when

class PostRepositoryImplTest {

    private lateinit var repository: PostRepositoryImpl

    private val mockApi = mock<RemoteApis> {}
    private val mapper = PostMapper()

    private val postId = "1"

    private val remoteItem = createPost()

    private val remoteList = listOf(remoteItem)

    @Before
    fun setUp() {
        repository = PostRepositoryImpl(mockApi, mapper)
    }

    @Test
    fun getPosts() {

        _when(mockApi.getPosts()).thenReturn(Single.just(remoteList))

        val test = repository.getPosts().test()

        verify(mockApi).getPosts()
        test.assertValue(mapper.mapToDomain(remoteList))
    }
}