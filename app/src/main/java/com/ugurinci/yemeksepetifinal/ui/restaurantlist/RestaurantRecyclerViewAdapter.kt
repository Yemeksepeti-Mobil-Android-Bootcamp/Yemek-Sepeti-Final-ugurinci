package com.ugurinci.yemeksepetifinal.ui.restaurantlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ugurinci.yemeksepetifinal.data.remote.RestaurantItem
import com.ugurinci.yemeksepetifinal.data.remote.RestaurantList
import com.ugurinci.yemeksepetifinal.databinding.SingleRestaurantBinding

class RestaurantRecyclerViewAdapter(private val restaurantList: RestaurantList) :
    RecyclerView.Adapter<RestaurantRecyclerViewAdapter.ViewHolder>() {
    var itemClickListener: (RestaurantItem) -> Unit = {}

    class ViewHolder(
        val binding: SingleRestaurantBinding,
        val itemClickListener: (RestaurantItem) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(restaurantItem: RestaurantItem) {
            binding.apply {
                textViewRestaurantName.text = restaurantItem.name
                textViewRestaurantAddress.text = restaurantItem.address
                Glide.with(root.context).load(restaurantItem.image).into(imageView11)
                root.setOnClickListener {
                    itemClickListener(restaurantItem)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            SingleRestaurantBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, itemClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(restaurantList[position])
    }

    override fun getItemCount() = restaurantList.size
}