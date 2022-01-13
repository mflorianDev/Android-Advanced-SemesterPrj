package com.example.natureobserverv2.fragments.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.natureobserverv2.data.repository
import com.example.natureobserverv2.web.WeatherWebEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherViewModel: ViewModel() {

    fun weather(): MutableLiveData<WeatherWebEntity> = repository.weather

    fun getWeatherInfo(city: String){
            repository.getWeatherInfo(city)
    }

    override fun onCleared() {
        super.onCleared()
    }
}