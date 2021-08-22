package com.ugurinci.yemeksepetifinal.data.repository

import com.ugurinci.yemeksepetifinal.data.api.MockAPIService
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class RestaurantDetailRepository @Inject constructor(private val mockAPIService: MockAPIService) {
    fun fetchRestaurantsById(id: String) = flow {
        val response = try {
            mockAPIService.getRestaurantById(id)
        } catch (e: Exception) {
            null
        }
        emit(response)
    }

    fun fetchFoods() = flow {
        val response = try {
            mockAPIService.getFoods()
        } catch (e: Exception) {
            null
        }
        emit(response)
    }
}