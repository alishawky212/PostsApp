package com.example.data

import com.example.data.remotemodels.RemoteAddress
import com.example.data.remotemodels.RemoteComment
import com.example.data.remotemodels.RemoteCompany
import com.example.data.remotemodels.RemoteGeo
import com.example.data.remotemodels.RemotePost
import com.example.data.remotemodels.RemoteUser

fun createUser(): RemoteUser = RemoteUser(id = "1", name = "name", username = "username", email = "email", address = createAddress(), phone = "phone", website = "website", company = createCompany())
fun createGeo(): RemoteGeo = RemoteGeo("0.0", "0.0")
fun createAddress(): RemoteAddress = RemoteAddress(street = "street", suite = "suite", city = "city", zipcode = "zipcode", geo = createGeo())
fun createCompany(): RemoteCompany = RemoteCompany(name = "name", catchPhrase = "catchPhrase", bs = "bs")

fun createPost(): RemotePost = RemotePost(id = "1", userId = "1", title = "title", body = "body")

fun createComment(): RemoteComment = RemoteComment(id = "1", postId = "1", name = "name", email = "email", body = "body")