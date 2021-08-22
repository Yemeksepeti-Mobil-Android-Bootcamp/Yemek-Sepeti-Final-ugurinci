package com.ugurinci.yemeksepetifinal.data.remote

import com.google.gson.annotations.SerializedName

data class FoodItem(
    @SerializedName("explanation")
    val explanation: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("restaurantId")
    val restaurantId: String
)