package com.example.postsapplication.master.view

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.transition.TransitionInflater
import com.example.postsapplication.R
import com.example.postsapplication.ViewModelFactory
import com.example.postsapplication.master.view.adpter.PostsAdapter
import com.example.postsapplication.master.viewModel.MasterViewModel
import com.example.postsapplication.models.PostItem
import com.example.postsapplication.models.PostsState
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject
import jp.wasabeef.recyclerview.animators.FadeInLeftAnimator
import com.example.postsapplication.detail.DetailFragment
import com.example.postsapplication.observe
import com.example.postsapplication.startRefreshing
import com.example.postsapplication.stopRefreshing
import com.example.postsapplication.withViewModel
import kotlinx.android.synthetic.main.fragment_master.*
import kotlinx.android.synthetic.main.post_item.*

class MasterFragment : Fragment(), PostsAdapter.OnPostClicked {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var postsAdapter: PostsAdapter

    private lateinit var viewModel: MasterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
        viewModel = withViewModel(viewModelFactory) {
            observe(getPostsLiveData(), ::updatePosts)
            getAllPosts()
        }
    }

    private fun updatePosts(postsState: PostsState<PostItem>?) {
        postsState?.let {
            when (it) {
                PostsState.LoadingState -> swipeRefreshLayout.startRefreshing()
                is PostsState.DataState -> showData(it)
                is PostsState.ErrorState -> showError(it)
            }
        }
    }

    private fun showError(it: PostsState.ErrorState<PostItem>) {
        swipeRefreshLayout.stopRefreshing()
        Toast.makeText(context, it.error, Toast.LENGTH_LONG).show()
    }

    private fun showData(it: PostsState.DataState<PostItem>) {
        swipeRefreshLayout.stopRefreshing()
        postsAdapter.setPosts(it.data)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_master, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(context!!, R.color.colorAccent))
        swipeRefreshLayout.setOnRefreshListener { viewModel.getAllPosts() }
        val fadeInLeftAnimator = FadeInLeftAnimator()
        fadeInLeftAnimator.addDuration = 500
        postsRecycler.addItemDecoration(DividerItemDecoration(postsRecycler.context, DividerItemDecoration.VERTICAL))
        postsRecycler.itemAnimator = fadeInLeftAnimator
        postsAdapter.setOnPostClickListener(this)
        postsRecycler.adapter = postsAdapter
    }

    override fun onClickPost(post: PostItem) {
        val detailFragment = DetailFragment.newInstance(post = post)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            sharedElementReturnTransition = TransitionInflater.from(
                activity
            ).inflateTransition(R.transition.change_image_trans)
            exitTransition = TransitionInflater.from(
                activity
            ).inflateTransition(android.R.transition.fade)

            detailFragment.sharedElementEnterTransition = TransitionInflater.from(
                activity
            ).inflateTransition(R.transition.change_image_trans)
            detailFragment.enterTransition = TransitionInflater.from(
                activity
            ).inflateTransition(android.R.transition.fade)
        }
        fragmentManager?.apply {
            beginTransaction()
                .replace(R.id.container, detailFragment)
                .addToBackStack("Detail")
                .addSharedElement(user_image, getString(R.string.user_avatar))
                .commit()
        }
    }
}
