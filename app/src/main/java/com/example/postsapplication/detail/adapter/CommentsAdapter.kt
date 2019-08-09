package com.example.postsapplication.detail.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.postsapplication.R
import com.example.postsapplication.inflate
import com.example.postsapplication.loadAvatar
import com.example.postsapplication.models.CommentItem
import kotlinx.android.synthetic.main.comment_item.view.*
import javax.inject.Inject

class CommentsAdapter @Inject constructor() : RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder>() {

    private val commentsList = ArrayList<CommentItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        return CommentsViewHolder(parent.inflate(R.layout.comment_item))
    }

    override fun getItemCount(): Int {
        return commentsList.size
    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        val comment = commentsList[position]
        holder.bind(comment)
    }

    fun setComments(comments: List<CommentItem>) {
        commentsList.clear()
        commentsList.addAll(comments)
        notifyItemRangeChanged(0, commentsList.size)
    }

    inner class CommentsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(comment: CommentItem) {
            with(itemView) {
                userAvatar.loadAvatar(comment.email)
                userName.text = comment.name
                commentBody.text = comment.body
            }
        }
    }
}