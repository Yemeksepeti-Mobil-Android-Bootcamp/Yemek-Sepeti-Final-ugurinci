package com.ugurinci.yemeksepetifinal.data.remote

import com.google.gson.annotations.SerializedName

data class RestaurantItem(
    @SerializedName("address")
    val address: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone")
    val phone: String
)