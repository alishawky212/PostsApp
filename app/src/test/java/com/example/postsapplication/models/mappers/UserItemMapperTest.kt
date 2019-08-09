package com.example.postsapplication.models.mappers

import com.example.postsapplication.createUser
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class UserItemMapperTest {

    private lateinit var mapper: UserItemMapper

    @Before
    fun setUp() {
        mapper = UserItemMapper()
    }

    @Test
    fun mapToPresentation() {
        val user = createUser()

        val userItem = mapper.mapToPresentation(user)

        assertTrue(userItem.id == user.id)
        assertTrue(userItem.username == user.username)
        assertTrue(userItem.email == user.email)
    }
}