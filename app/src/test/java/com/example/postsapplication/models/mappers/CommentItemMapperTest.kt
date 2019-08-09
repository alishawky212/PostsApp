package com.example.postsapplication.models.mappers

import com.example.postsapplication.createComment
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.Before

class CommentItemMapperTest {
    private lateinit var mapper: CommentItemMapper

    @Before
    fun setUp() {
        mapper = CommentItemMapper()
    }
    @Test
    fun mapToPresentation() {
        val comment = createComment()

        val commentItem = mapper.mapToPresentation(listOf(comment))[0]

        assertTrue(commentItem.postId == comment.postId)
        assertTrue(commentItem.id == comment.id)
        assertTrue(commentItem.name == comment.name)
        assertTrue(commentItem.email == comment.email)
        assertTrue(commentItem.body == comment.body)
    }
}