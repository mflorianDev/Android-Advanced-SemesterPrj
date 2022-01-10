package com.example.natureobserverv2.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ObservationDAO {

    @Insert
    suspend fun addObservation(observation: Observation)

    @Query("SELECT * FROM observation_table ORDER BY date ASC")
    fun readAll(): LiveData<List<Observation>>
}