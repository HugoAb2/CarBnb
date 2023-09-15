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
                "99",
                "Brazil",
                "adm@gmail.com",
                "******",
                ArrayList<String>(),
                ArrayList<String>(),
                R.drawable.ic_noprofile))

            return list
        }

    }
}