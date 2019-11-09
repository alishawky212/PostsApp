package com.example.postsapplication.master.states

import com.example.android.architecture.blueprints.todoapp.mvibase.MviViewState
import com.example.postsapplication.models.PostItem

data class PostsViewState(
    val isLoading:Boolean,
    val posts:List<PostItem>,
    val error:Throwable?
) : MviViewState