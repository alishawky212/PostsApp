package com.example.postsapplication

import com.example.domain.models.*



fun createUser(): User = User(id = "1", name = "name", username = "username", email = "email",address =  createAddress(), phone = "phone", website = "website", company = createCompany())
fun createGeo(): Geo = Geo("0.0", "0.0")
fun createAddress(): Address = Address(street = "street", suite = "suite", city = "city",zipcode =  "zipcode", geo = createGeo())
fun createCompany(): Company = Company(name = "name",catchPhrase =  "catchPhrase",bs =  "bs")

fun createPost(): Post = Post(id = "1",userId =  "1",title =  "title",body= "body")

fun createComment(): Comment = Comment(id = "1", postId = "1", name = "name",email = "email",body =  "body")