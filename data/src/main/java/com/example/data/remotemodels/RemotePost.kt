package com.example.data.remotemodels


import com.google.gson.annotations.SerializedName

data class RemotePost(
    @SerializedName("body")
    val body: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("userId")
    val userId: Int,
    val user: RemoteUser,
    val comments:List<RemoteComment>
)