package com.ugurinci.yemeksepetifinal.ui.restaurantlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ugurinci.yemeksepetifinal.data.remote.RestaurantList
import com.ugurinci.yemeksepetifinal.data.repository.RestaurantListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantListViewModel @Inject constructor(private val restaurantListRepository: RestaurantListRepository) :
    ViewModel() {

    private var _restaurants = MutableLiveData<RestaurantList>()

    val restaurants: LiveData<RestaurantList> = _restaurants

    init {
        fetchRestaurants()
    }

    private fun fetchRestaurants() = viewModelScope.launch {
        restaurantListRepository.fetchRestaurants().collect {
            if (it != null) {
                _restaurants.value = it.body()
            }
        }
    }
}