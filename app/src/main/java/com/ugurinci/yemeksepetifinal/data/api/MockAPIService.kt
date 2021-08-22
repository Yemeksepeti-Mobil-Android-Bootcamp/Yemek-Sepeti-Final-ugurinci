package com.ugurinci.yemeksepetifinal.data.api

import com.ugurinci.yemeksepetifinal.data.remote.FoodItem
import com.ugurinci.yemeksepetifinal.data.remote.FoodList
import com.ugurinci.yemeksepetifinal.data.remote.RestaurantItem
import com.ugurinci.yemeksepetifinal.data.remote.RestaurantList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MockAPIService {
    @GET("restaurants")
    suspend fun getRestaurants(): Response<RestaurantList>

    @GET("restaurants/{id}")
    suspend fun getRestaurantById(@Path("id") id: String): Response<RestaurantItem>

    @GET("foods")
    suspend fun getFoods(): Response<FoodList>

    @GET("foods/{id}")
    suspend fun getFoodById(@Path("id") id: String): Response<FoodItem>
}