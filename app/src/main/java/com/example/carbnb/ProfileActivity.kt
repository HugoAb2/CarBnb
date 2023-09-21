package com.example.carbnb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.carbnb.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding : ActivityProfileBinding
    private lateinit var username : TextView
    private lateinit var email : TextView
    private lateinit var profileImage : ImageView
    private lateinit var userID : String
    private lateinit var name : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //name = intent.getStringExtra("username") as String
        //userID = intent.getStringExtra("userID") as String
        username = binding.username
        email = binding.email
        profileImage = binding.profileImage
    }

    override fun onResume() {
        super.onResume()
        //username.text = name

    }
}