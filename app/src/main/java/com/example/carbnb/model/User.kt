package com.example.carbnb.model

import java.io.Serializable

data class User(
    val id: String,
    var name : String,
    var age : String,
    var country : String,
    var email : String,
    var password : String,
    var comments : ArrayList<String>,
    var friends : ArrayList<String>,
    var profile : Int?
    ): Serializable
