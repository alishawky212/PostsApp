package com.example.data.remotemodels


import com.google.gson.annotations.SerializedName

data class RemoteUser(
    @SerializedName("address")
    val address: RemoteAddress,
    @SerializedName("company")
    val company: RemoteCompany,
    @SerializedName("email")
    val email: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("website")
    val website: String
)

data class RemoteAddress(
    @SerializedName("city")
    val city: String,
    @SerializedName("geo")
    val geo: RemoteGeo,
    @SerializedName("street")
    val street: String,
    @SerializedName("suite")
    val suite: String,
    @SerializedName("zipcode")
    val zipcode: String
)

data class RemoteCompany(
    @SerializedName("bs")
    val bs: String,
    @SerializedName("catchPhrase")
    val catchPhrase: String,
    @SerializedName("name")
    val name: String
)

data class RemoteGeo(
    @SerializedName("lat")
    val lat: String,
    @SerializedName("lng")
    val lng: String
)