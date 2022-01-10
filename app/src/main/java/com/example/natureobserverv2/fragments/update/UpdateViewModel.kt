package com.example.natureobserverv2.fragments.update

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.natureobserverv2.data.Observation
import com.example.natureobserverv2.data.repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UpdateViewModel: ViewModel() {

    fun updateObservation(observation: Observation){
        // create background thread (coroutine)
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateObservation(observation)
        }
    }

    fun deleteObservation(observation: Observation){
        // create background thread (coroutine)
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteObservation(observation)
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}