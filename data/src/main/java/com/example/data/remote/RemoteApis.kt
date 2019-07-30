package com.example.data.remote

import com.example.data.remotemodels.RemoteComment
import com.example.data.remotemodels.RemotePost
import com.example.data.remotemodels.RemoteUser
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RemoteApis {
    @GET("posts/")
    fun getPosts(): Single<List<RemotePost>>

    @GET("posts/{id}")
    fun getPost(@Path("id") postId: String): Single<RemotePost>

    @GET("users/")
    fun getUsers(): Single<List<RemoteUser>>

    @GET("users/{id}")
    fun getUser(@Path("id") userId: String): Single<RemoteUser>

    @GET("comments")
    fun getPostComments(@Query("postId") postId:String):Single<List<RemoteComment>>
}