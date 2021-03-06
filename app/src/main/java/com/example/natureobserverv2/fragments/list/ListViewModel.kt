package com.example.natureobserverv2.fragments.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.natureobserverv2.data.Observation
import com.example.natureobserverv2.data.repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel: ViewModel() {

    fun readObservations(): LiveData<List<Observation>> = repository.observations

    fun deleteAllObservations(){
        // create background thread (coroutine)
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllObservations()
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}