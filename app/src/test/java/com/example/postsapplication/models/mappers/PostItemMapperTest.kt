package com.example.postsapplication.models.mappers

import com.example.domain.usecase.CombinedUserPost
import com.example.postsapplication.createPost
import com.example.postsapplication.createUser
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.Before

class PostItemMapperTest {

    lateinit var mapper: PostItemMapper

    @Before
    fun setUp() {
        mapper = PostItemMapper()
    }

    @Test
    fun mapToPresentation() {
        val user = createUser()
        val post = createPost()
        val combinedUserPost = CombinedUserPost(user, post)

        val postItem = mapper.mapToPresentation(listOf(combinedUserPost))[0]

        assertTrue(postItem.postId == post.id)
        assertTrue(postItem.userId == user.id)
        assertTrue(postItem.title == post.title)
        assertTrue(postItem.body == post.body)
        assertTrue(postItem.name == user.name)
        assertTrue(postItem.username == user.username)
        assertTrue(postItem.email == user.email)
    }
}