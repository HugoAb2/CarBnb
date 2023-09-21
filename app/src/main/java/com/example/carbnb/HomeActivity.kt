package com.example.carbnb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.widget.AppCompatSeekBar
import androidx.cardview.widget.CardView
import com.example.carbnb.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding

    private lateinit var profileButton : CardView
    private lateinit var distanceButton : ImageView
    private lateinit var distanceKM : TextView
    private lateinit var seekBar: AppCompatSeekBar
    private var km = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        profileButton = binding.profile
        distanceButton = binding.locationButton
        distanceKM = binding.distanceKM
    }

    override fun onResume() {
        super.onResume()
        seekBar = binding.distanceSeekBar

        distanceButton.setOnClickListener {
            seekBar.visibility = View.VISIBLE
        }

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