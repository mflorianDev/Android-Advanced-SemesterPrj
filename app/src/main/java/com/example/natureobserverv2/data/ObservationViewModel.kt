package com.example.natureobserverv2.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ObservationViewModel(application: Application): AndroidViewModel(application) {

    private val observations: LiveData<List<Observation>>
    private val repository: ObservationRepository

    init {
        val observationDAO = ObservationDatabase.getDatabase(application).observationDAO()
        repository = ObservationRepository(observationDAO)
        observations = repository.observations
    }

    fun addObservation(observation: Observation){
        // create background thread (coroutine)
        viewModelScope.launch(Dispatchers.IO) {
            repository.addObservation(observation)
        }
    }

}