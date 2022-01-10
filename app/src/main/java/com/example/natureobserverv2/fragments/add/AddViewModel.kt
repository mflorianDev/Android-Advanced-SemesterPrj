package com.example.natureobserverv2.fragments.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.natureobserverv2.data.Observation
import com.example.natureobserverv2.data.ObservationDatabase
import com.example.natureobserverv2.data.ObservationRepository
import com.example.natureobserverv2.data.repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddViewModel: ViewModel() {

    fun addObservation(observation: Observation){
        // create background thread (coroutine)
        viewModelScope.launch(Dispatchers.IO) {
            repository.addObservation(observation)
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}