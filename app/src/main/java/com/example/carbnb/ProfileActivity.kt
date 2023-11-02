package com.example.carbnb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.carbnb.databinding.ActivityProfileBinding
import com.example.carbnb.model.User

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding : ActivityProfileBinding
    private lateinit var username : TextView
    private lateinit var email : TextView
    private lateinit var password : TextView
    private lateinit var profileImage : ImageView
    private lateinit var saveButton : Button
    private lateinit var deleteButton : Button

    private lateinit var userIn : User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        @Suppress("DEPRECATION")
        userIn = intent.getSerializableExtra("user") as User

        profileImage = binding.profileImage
        username = binding.username
        email = binding.email
        password = binding.password
        saveButton = binding.saveChangesButton
        deleteButton = binding.deleteAccountButton
    }

    override fun onResume() {
        super.onResume()

        username.text = userIn.name
        email.text = userIn.email
        password.text = userIn.password
        if(userIn.profile != null) profileImage.setImageResource(userIn.profile!!)

    }
}