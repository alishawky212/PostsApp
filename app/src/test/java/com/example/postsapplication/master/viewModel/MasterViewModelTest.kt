package com.example.postsapplication.master.viewModel

import com.example.domain.usecase.CombinedUserPost
import com.example.domain.usecase.UsersPostsUseCase
import com.example.postsapplication.createPost
import com.example.postsapplication.createUser
import com.example.postsapplication.models.PostsState
import com.example.postsapplication.models.mappers.PostItemMapper
import com.nhaarman.mockito_kotlin.mock
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

import org.mockito.Mockito.`when` as _when
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.postsapplication.createPostItem
import org.junit.Rule



class MasterViewModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    private val useCase = mock<UsersPostsUseCase> {}

    private val mapper: PostItemMapper = PostItemMapper()

    private lateinit var viewModel: MasterViewModel

    private val user = createUser()
    private val post = createPost()

    private val combinedUserPosts = listOf(CombinedUserPost(user, post))

    private val postItem = listOf(createPostItem())

    @Before
    fun setUp() {
        viewModel = MasterViewModel(useCase, mapper, Schedulers.trampoline())
    }

    @Test
    fun getAllPosts() {
        _when(useCase.get()).thenReturn(Single.just(combinedUserPosts))

        viewModel.getAllPosts()

        val postItem = viewModel.getPostsLiveData().value

        assertEquals(post.id, (postItem as PostsState.DataState).data[0].postId)
    }
}