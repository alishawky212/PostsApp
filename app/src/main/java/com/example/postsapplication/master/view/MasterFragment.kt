package com.example.postsapplication.master.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.postsapplication.R
import com.example.postsapplication.ViewModelFactory
import com.example.postsapplication.master.view.adpter.PostsAdapter
import com.example.postsapplication.master.viewModel.MasterViewModel
import com.example.postsapplication.models.PostItem
import com.example.postsapplication.models.PostsState
import com.example.postsapplication.observe
import com.example.postsapplication.withViewModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_master.*
import javax.inject.Inject


class MasterFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var postsAdapter: PostsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)

        withViewModel<MasterViewModel>(viewModelFactory) {
            observe(getPostsLiveData(), ::updatePosts)
            getAllPosts()
        }
    }

    private fun updatePosts(postsState: PostsState<PostItem>?) {
        postsState?.let {
            when(it){
                PostsState.LoadingState ->{

                }
               is PostsState.DataState ->{
                   postsAdapter.setPosts(it.data)
               }
               is PostsState.ErrorState ->{

               }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_master, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postsRecycler.adapter = postsAdapter



    }
}
