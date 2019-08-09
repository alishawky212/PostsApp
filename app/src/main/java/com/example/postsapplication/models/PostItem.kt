package com.example.postsapplication.models

import android.os.Parcel
import android.os.Parcelable

data class PostItem(val postId: String, val userId: String, val title: String, val body: String, val name: String, val username: String, val email: String) : Parcelable {
    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<PostItem> = object : Parcelable.Creator<PostItem> {
            override fun createFromParcel(source: Parcel): PostItem = PostItem(source)
            override fun newArray(size: Int): Array<PostItem?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
    source.readString(),
    source.readString(),
    source.readString(),
    source.readString(),
    source.readString(),
    source.readString(),
    source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(postId)
        writeString(userId)
        writeString(title)
        writeString(body)
        writeString(name)
        writeString(username)
        writeString(email)
    }
}