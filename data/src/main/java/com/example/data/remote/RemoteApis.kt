package com.example.data.remote

import com.example.data.remotemodels.RemoteComment
import com.example.data.remotemodels.RemotePost
import com.example.data.remotemodels.RemoteUser
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteApis {
    @GET("posts/")
    fun getPosts(): Single<List<RemotePost>>

    @GET("users/")
    fun getUsers(): Single<List<RemoteUser>>

    @GET("comments")
    fun getPostComments(@Query("postId") postId: String): Single<List<RemoteComment>>
}