package com.example.natureobserverv2.data

import androidx.lifecycle.LiveData

lateinit var repository: ObservationRepository

class ObservationRepository(private val observationDAO: ObservationDAO) {

    val observations: LiveData<List<Observation>> = observationDAO.readAll()

    suspend fun addObservation(observation: Observation){
        observationDAO.addObservation(observation)
    }
}