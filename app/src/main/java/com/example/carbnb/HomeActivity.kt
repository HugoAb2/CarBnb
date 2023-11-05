package com.example.carbnb

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.AppCompatSeekBar
import androidx.cardview.widget.CardView
import com.example.carbnb.databinding.ActivityHomeBinding
import com.example.carbnb.model.User

class HomeActivity : AppCompatActivity() {    

    private lateinit var binding : ActivityHomeBinding

    private lateinit var profileImage: ImageView
    private lateinit var profileButton : CardView
    private lateinit var distanceButton : ImageView
    private lateinit var distanceKM : TextView
    private lateinit var username : TextView
    private lateinit var seekBar: AppCompatSeekBar

    //private val userID = FirebaseAuth.getInstance().currentUser!!.uid
    //private val database = Firebase.database
    //private val userData = database.getReference("Users")
    private lateinit var userIn : User
    private var km = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        profileButton = binding.profile
        profileImage = binding.profileImage
        distanceButton = binding.locationButton
        distanceKM = binding.distanceKM
        username = binding.username

        @Suppress("DEPRECATION")
        userIn = intent.getSerializableExtra("user") as User
    }

    override fun onResume() {
        super.onResume()

        logUserData()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, FeedFragment())
            .commit()

        seekBar = binding.distanceSeekBar
        distanceButton.setOnClickListener {
            seekBar.visibility = View.VISIBLE
        }

        initSeekbar()

        profileButton.setOnClickListener {
            val profileActivity = Intent(this, ProfileActivity::class.java)
            profileActivity.putExtra("user", userIn)
            launcher.launch(profileActivity)
        }
    }

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode == Activity.RESULT_OK){
            val data = it.data
            if (data != null && data.getBooleanExtra("delete", false)) {
                val deleteIntent = Intent(this, LoginActivity::class.java)
                startActivity(deleteIntent)
                finish()
                Toast.makeText(this, "Profile Deleted", Toast.LENGTH_SHORT).show()
            }
            else Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
        }
        if (it.resultCode == Activity.RESULT_CANCELED){
            Toast.makeText(this, "Changes not applied", Toast.LENGTH_SHORT).show()
        }
    }

    private fun logUserData(){
        /*userData.child(userID).get().addOnSuccessListener {
            name = it.child("name").value.toString()
            username.text = name
        }*/
        username.text = userIn.name
        if (userIn.profile != null) profileImage.setImageResource(userIn.profile!!)
    }
    private fun initSeekbar(){
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                var string = seekBar?.progress.toString() + "KM"
                distanceKM.text = string
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                km = seekBar!!.progress
                seekBar.visibility = View.GONE
            }

        })

    }
}