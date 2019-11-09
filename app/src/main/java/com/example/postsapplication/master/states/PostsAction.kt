package com.example.postsapplication.master.states

import com.example.android.architecture.blueprints.todoapp.mvibase.MviAction
import com.example.postsapplication.models.PostItem

sealed class PostsAction : MviAction {
    object LoadPostsAction : PostsAction()

    data class openPostDetailAction(val post: PostItem) : PostsAction()
}