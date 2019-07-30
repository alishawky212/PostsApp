package com.example.postsapplication.models.mappers

import com.example.domain.usecase.CombinedUserPost
import com.example.postsapplication.models.PostItem
import javax.inject.Inject

class PostItemMapper @Inject constructor() {
    fun mapToPresentation(combined: CombinedUserPost):PostItem =
            PostItem(postId = combined.post.id,name = combined.user.name,
                body = combined.post.body,userId = combined.user.id,email = combined.user.email,title = combined.post.title,
                username = combined.user.username)

    fun mapToPresentation(combined: List<CombinedUserPost>):List<PostItem> =
           combined.map { mapToPresentation(it) }
}