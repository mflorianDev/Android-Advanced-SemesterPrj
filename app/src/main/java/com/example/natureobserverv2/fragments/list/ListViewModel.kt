package com.example.natureobserverv2.fragments.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.natureobserverv2.data.Observation
import com.example.natureobserverv2.data.repository

class ListViewModel: ViewModel() {

    fun readObservations(): LiveData<List<Observation>> = repository.observations

    override fun onCleared() {
        super.onCleared()
    }
}