package com.ugurinci.yemeksepetifinal.data.repository

import com.ugurinci.yemeksepetifinal.data.api.MockAPIService
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class RestaurantListRepository @Inject constructor(private val mockAPIService: MockAPIService) {
    fun fetchRestaurants() = flow {
        val response = try {
            mockAPIService.getRestaurants()
        } catch (e: Exception) {
            null
        }
        emit(response)
    }
}