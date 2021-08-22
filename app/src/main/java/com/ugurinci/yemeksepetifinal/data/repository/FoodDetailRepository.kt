package com.ugurinci.yemeksepetifinal.data.repository

import com.ugurinci.yemeksepetifinal.data.api.MockAPIService
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class FoodDetailRepository @Inject constructor(private val mockAPIService: MockAPIService) {
    fun fetchFoodById(id: String) = flow {
        val response = try {
            mockAPIService.getFoodById(id)
        } catch (e: Exception) {
            null
        }
        emit(response)
    }
}