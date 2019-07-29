package com.example.postsapplication.master.view.adpter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.postsapplication.R
import com.example.postsapplication.inflate
import com.example.postsapplication.loadAvatar
import com.example.postsapplication.models.PostItem
import kotlinx.android.synthetic.main.post_item.view.*
import javax.inject.Inject

class PostsAdapter @Inject constructor() : RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {

    private val postsList:ArrayList<PostItem> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        return PostsViewHolder(parent.inflate(R.layout.post_item))
    }

    override fun getItemCount(): Int {
        return postsList.size
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        val post = postsList[position]
        holder.bind(post)
    }

    fun setPosts(postsList:List<PostItem>){
        this.postsList.addAll(postsList)
        notifyDataSetChanged()
    }

    inner class PostsViewHolder(view:View):RecyclerView.ViewHolder(view){

        fun bind(post:PostItem){
            with(itemView){
                user_image.loadAvatar(post.email)
                tvTitle.text = post.title
                tvBody.text = post.body
            }
        }
    }
}