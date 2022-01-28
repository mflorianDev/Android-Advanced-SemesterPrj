package com.example.natureobserverv2.data

import androidx.lifecycle.LiveData
import androidx.room.*

// CRUD operations for database
@Dao
interface ObservationDAO {

    // CREATE, suspend fun for use of kotlin coroutines
    @Insert
    suspend fun addObservation(observation: Observation)

    // READ ALL
    @Query("SELECT * FROM observation_table ORDER BY date DESC")
    fun readAll(): LiveData<List<Observation>>

    // UPDATE, suspend fun for use of kotlin coroutines
    @Update
    suspend fun updateObservation(observation: Observation)

    // DELETE, suspend fun for use of kotlin coroutines
    @Delete
    suspend fun deleteObservation(observation: Observation)

    // DELETE ALL, suspend fun for use of kotlin coroutines
    @Query("DELETE FROM observation_table")
    suspend fun deleteAllObservations()

    // CHECK IF EMPTY -> response mit fragezeichen damit null zurückgegeben werden kann
    @Query("SELECT * FROM observation_table LIMIT 1")
    fun anyData(): Observation?

}