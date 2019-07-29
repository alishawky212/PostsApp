package com.example.postsapplication.models.mappers

import com.example.domain.models.User
import com.example.postsapplication.models.UserItem
import javax.inject.Inject

class UserItemMapper @Inject constructor() {

    fun mapToPresentation(user: User): UserItem = UserItem(id = user.id,username = user.username,email = user.email)

    fun mapToPresentation(list: List<User>): List<UserItem> = list.map { mapToPresentation(it) }

}