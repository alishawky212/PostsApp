package com.example.data.mappers

import com.example.data.createPost
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class PostMapperTest {

    lateinit var postMapper: PostMapper

    @Before
    fun setUp() {
        postMapper = PostMapper()
    }

    @Test
    fun mapToDomain() {
        val remotePost = createPost()

        val domainModel = postMapper.mapToDomain(remotePost)

        assertTrue(domainModel.id == remotePost.id)
        assertTrue(domainModel.body == remotePost.body)
        assertTrue(domainModel.title == remotePost.title)
        assertTrue(domainModel.userId == remotePost.userId)
    }
}