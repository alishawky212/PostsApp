package com.example.data.mappers

import com.example.data.createComment
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class CommentMapperTest {

    private lateinit var commentMapper: CommentMapper

    @Before
    fun setUp() {
        commentMapper = CommentMapper()
    }

    @Test
    fun mapToDomain() {
        val remoteComment = createComment()

        val domainModel = commentMapper.mapToDomain(remoteComment)

        assertTrue(domainModel.id == remoteComment.id)
        assertTrue(domainModel.postId == remoteComment.postId)
        assertTrue(domainModel.body == remoteComment.body)
        assertTrue(domainModel.name == remoteComment.name)
        assertTrue(domainModel.email == remoteComment.email)
    }
}