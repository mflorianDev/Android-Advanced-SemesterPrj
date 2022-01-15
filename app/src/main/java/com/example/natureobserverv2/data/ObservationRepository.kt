package com.example.natureobserverv2.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.natureobserverv2.web.*
import retrofit2.*

lateinit var repository: ObservationRepository

class ObservationRepository(private val observationDAO: ObservationDAO, private val weatherWebService: WeatherWebService) {

    val observations: LiveData<List<Observation>> = observationDAO.readAll()
    var weather: MutableLiveData<WeatherWebEntity> = MutableLiveData()
    var weatherError: MutableLiveData<Int> = MutableLiveData(0)

    suspend fun addObservation(observation: Observation){
        observationDAO.addObservation(observation)
    }

    suspend fun updateObservation(observation: Observation){
        observationDAO.updateObservation(observation)
    }

    suspend fun deleteObservation(observation: Observation){
        observationDAO.deleteObservation(observation)
    }

    suspend fun deleteAllObservations(){
        observationDAO.deleteAllObservations()
    }


    fun getWeatherInfo( city: String) {
        val geoCode = city
        val units = "metric"
        val lang = "de"
        val apiKey = "d5728be66b249b5ad501e60868036f1f"
        weatherWebService.getWeatherInfo(geoCode, units, lang, apiKey).enqueue(object: Callback<WeatherWebEntity> {
            override fun onResponse(
                call: Call<WeatherWebEntity>,
                response: Response<WeatherWebEntity>
            ) {
                Log.i("Response", response.toString())
                // check if statuscode in range 200-299
                if (response.isSuccessful){
                    //weather = MutableLiveData(response.body())
                    // to keep all observers alive actualise MutableLiveData with .postValue()!!
                    weather.postValue(response.body())
                    Log.i("Response.body", response.body().toString())
                } else {
                    weatherError.postValue(response.code())
                    // statusCode: 401 -> invalid APIkey
                    // statusCode: 404 -> city not found
                    Log.e("WeatherWebService-Request-Error", response.raw().toString())
                    Log.e("Response.body", response.body().toString())
                }
            }
            override fun onFailure(call: Call<WeatherWebEntity>, t: Throwable) {
                Log.e("HTTP", "Get weather info failed", t)
            }
        }
        )
    }

}