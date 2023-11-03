package com.example.carbnb.dao

import com.example.carbnb.R
import com.example.carbnb.model.User

class UsersDataSource {

    companion object{

        fun createUsersList(): ArrayList<User> {
            val list = ArrayList<User>()

            list.add(User(
                "U7gHrfN7NLPLgvf7FI11Sk8dbvI2",
                "adm",
                "adm@gmail.com",
                "******",
                R.drawable.whind))

            return list
        }

    }
}