package com.example.postsapplication.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.postsapplication.R
import com.example.postsapplication.ViewModelFactory
import com.example.postsapplication.detail.adapter.CommentsAdapter
import com.example.postsapplication.detail.viewModel.DetailViewModel
import com.example.postsapplication.loadAvatar
import com.example.postsapplication.models.CommentItem
import com.example.postsapplication.models.PostItem
import com.example.postsapplication.models.PostsState
import com.example.postsapplication.observe
import com.example.postsapplication.withViewModel
import dagger.android.support.AndroidSupportInjection
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator
import kotlinx.android.synthetic.main.fragment_detail.*
import javax.inject.Inject

private const val ARG_POST = "postItem"

class DetailFragment : Fragment() {

    private var postItem: PostItem? = null

    @Inject
    lateinit var adapter: CommentsAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
        arguments?.let {
            postItem = it.getParcelable(ARG_POST)
        }

        viewModel = withViewModel(viewModelFactory) {
            postItem?.let {
                getComments(it.postId)
            }
            observe(getCommentsList(), ::updateComments)
        }
    }

    private fun updateComments(commentsState: PostsState<CommentItem>?) {
        commentsState?.let {
            when (it) {
                PostsState.LoadingState -> Log.d("Loading", "LoadingComments")
                is PostsState.DataState -> showData(it)
                is PostsState.ErrorState -> showError(it)
            }
        }
    }

    private fun showError(it: PostsState.ErrorState<CommentItem>) {
        Toast.makeText(context, it.error, Toast.LENGTH_LONG).show()
    }

    private fun showData(it: PostsState.DataState<CommentItem>) {
        adapter.setComments(it.data)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCommentsRecyclerView()
        initPostView()
    }

    private fun initPostView() {
        postItem?.let {
            userAvatar.loadAvatar(it.email)
            userUsername.text = it.username
            postTitle.text = it.title
            postBody.text = it.body
        }
    }

    private fun initCommentsRecyclerView() {
        val slideInUpAnimator = SlideInUpAnimator()
        slideInUpAnimator.addDuration = 500
        commentsRecyclerView.addItemDecoration(
            DividerItemDecoration(
                commentsRecyclerView.context,
                DividerItemDecoration.VERTICAL
            )
        )
        commentsRecyclerView.itemAnimator = slideInUpAnimator
        commentsRecyclerView.adapter = adapter
    }

    companion object {
        @JvmStatic
        fun newInstance(post: PostItem) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_POST, post)
                }
            }
    }
}
