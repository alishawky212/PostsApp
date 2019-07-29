package com.example.domain.mapper

import com.example.domain.models.Post
import com.example.domain.models.User
import com.example.domain.usecase.CombinedUserPost
import javax.inject.Inject

class UserPostMapper @Inject constructor() {

    fun map(user: User, post: Post): CombinedUserPost = CombinedUserPost(user, post)

    fun map(userList: List<User>, postList: List<Post>): List<CombinedUserPost> = postList.map { post ->
        CombinedUserPost(userList.first { post.userId == it.id }, post)
    }
}