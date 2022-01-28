package com.example.natureobserverv2.fragments.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.example.natureobserverv2.data.repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    init {
        /*
        if (isDbEmpty()){
            loadObservationTestArray()
        }
         */
    }
    /*
    // check if database is already populated
    fun isDbEmpty(){
        // create background thread (coroutine)
        viewModelScope.launch(Dispatchers.IO) {
            Log.e("DbCheck", repository.anyData().toString())
            if(repository.anyData() == null){
                Log.e("Empty ROOM Database", "TestObservationArray is been loaded")
                return true
            }
        }
    }

    // load ObservationTestArray to Database
    private fun loadObservationTestArray(){
        val observationTestArray = repository.getObservationTestArray()
        for (observation in observationTestArray){
            // create background thread (coroutine)
            viewModelScope.launch(Dispatchers.IO) {
                repository.addObservation(observation)
            }
        }
    }
     */
    fun logInstantiation(){
        Log.i("MainViewModel: ", "instantiated")
    }

    override fun onCleared() {
        super.onCleared()
    }
}