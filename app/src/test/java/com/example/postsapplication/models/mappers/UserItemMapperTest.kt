package com.example.postsapplication.models.mappers

import com.example.postsapplication.createUser
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

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

        // then
        assertTrue(userItem.id == user.id)
        assertTrue(userItem.username == user.username)
        assertTrue(userItem.email == user.email)
    }
}