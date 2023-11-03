package com.example.carbnb.model

import java.io.Serializable

class Advertise (
    val id : Int,
    val ownerId : String,
    var date : String,
    var carName : String,
    var price : String,
    var location : String,
    var description : String?,
    var carImage : Int?
) : Serializable