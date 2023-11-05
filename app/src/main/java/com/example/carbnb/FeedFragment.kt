package com.example.carbnb

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.carbnb.adapters.AdvertiseAdapter
import com.example.carbnb.dao.AdvertisesDataSource
import com.example.carbnb.databinding.FragmentFeedBinding

class FeedFragment : Fragment() {

    private lateinit var binding: FragmentFeedBinding
    private lateinit var recyclerView: RecyclerView

    private val advertisesList = AdvertisesDataSource.createAdvertisesList()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentFeedBinding.bind(view)

        recyclerView = binding.recyclerViewAdvertises
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        recyclerView.adapter = AdvertiseAdapter(advertisesList){
            val intent = Intent(requireContext(), SchedulingActivity::class.java)
            intent.putExtra("advertiseID", it.id.toString())
            startActivity(intent)
        }
    }

}