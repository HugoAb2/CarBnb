package com.example.carbnb.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.carbnb.R
import com.example.carbnb.databinding.ItemMyAdvertiseBinding
import com.example.carbnb.model.Advertise


class MyAdvertisesAdapter(private val advertisesList: MutableList<Advertise>, private val onItemClicked : (Advertise)->Unit) :
    RecyclerView.Adapter<MyAdvertisesAdapter.AdvertiseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdvertiseViewHolder {
        val advertiseItemView = ItemMyAdvertiseBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return AdvertiseViewHolder(advertiseItemView)
    }

    override fun onBindViewHolder(holder: AdvertiseViewHolder, position: Int) {
        val advertise = advertisesList[position]
        holder.apply {
            if (advertise.carImage != null) carImage.setImageResource(R.drawable.bigblack_fusca)
            carName.text = advertise.model
            local.text = advertise.location
            description.text = advertise.description
            price.text = advertise.price
            date.text = advertise.date
            editButton.setOnClickListener {
                onItemClicked(advertise)
            }
            viewButton.setOnClickListener {
                advertise.model += " viewOP"
                onItemClicked(advertise)
            }
            deleteButton.setOnClickListener {
                advertise.model += " delete"
                onItemClicked(advertise)
            }
        }
    }

    override fun getItemCount(): Int {
        return advertisesList.size
    }

    class AdvertiseViewHolder(binding: ItemMyAdvertiseBinding): RecyclerView.ViewHolder(binding.root){
        val carImage = binding.carImage
        val carName = binding.carName
        val local = binding.location
        val description = binding.descriptionText
        val price = binding.price
        val date = binding.date
        val editButton = binding.editButton
        val viewButton = binding.viewButton
        val deleteButton = binding.deleteButton
    }
}