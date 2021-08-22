package com.ugurinci.yemeksepetifinal.ui.restaurantdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ugurinci.yemeksepetifinal.data.remote.FoodItem
import com.ugurinci.yemeksepetifinal.databinding.SingleFoodBinding

class FoodRecyclerViewAdapter(private val listFoodItem: List<FoodItem>) :
    RecyclerView.Adapter<FoodRecyclerViewAdapter.ViewHolder>() {
    var itemClickListener: (FoodItem) -> Unit = {}

    class ViewHolder(
        val binding: SingleFoodBinding,
        val itemClickListener: (FoodItem) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(foodItem: FoodItem) {
            binding.apply {
                textViewFoodName.text = foodItem.name
                textViewFoodPrice.text = foodItem.price.toString() + " TL"
                Glide.with(root.context).load(foodItem.image).into(imageViewFood)
                root.setOnClickListener {
                    itemClickListener(foodItem)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SingleFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, itemClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listFoodItem[position])
    }

    override fun getItemCount() = listFoodItem.size
}