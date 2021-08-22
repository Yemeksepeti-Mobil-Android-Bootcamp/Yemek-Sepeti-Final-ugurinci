package com.ugurinci.yemeksepetifinal.ui.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ugurinci.yemeksepetifinal.utils.Cart
import com.ugurinci.yemeksepetifinal.data.remote.FoodItem
import com.ugurinci.yemeksepetifinal.databinding.SingleCartBinding

class CartRecyclerViewAdapter : RecyclerView.Adapter<CartRecyclerViewAdapter.ViewHolder>() {
    var itemClickListener: (FoodItem) -> Unit = {}

    class ViewHolder(val binding: SingleCartBinding, val itemClickListener: (FoodItem) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(foodItem: FoodItem) {
            binding.apply {
                textViewCartName.text = foodItem.name
                textViewCartPrice.text = foodItem.price.toString() + " TL"
                Glide.with(root.context).load(foodItem.image).into(imageViewCart)
                imageButtonCartRemove.setOnClickListener {
                    itemClickListener(foodItem)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SingleCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, itemClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(Cart.foodList[position])
    }

    override fun getItemCount() = Cart.foodList.size
}