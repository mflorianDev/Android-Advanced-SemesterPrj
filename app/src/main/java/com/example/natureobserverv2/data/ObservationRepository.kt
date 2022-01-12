package com.example.natureobserverv2.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.natureobserverv2.web.WeatherWebEntity
import com.example.natureobserverv2.web.WeatherWebService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

lateinit var repository: ObservationRepository

class ObservationRepository(private val observationDAO: ObservationDAO, private val weatherWebService: WeatherWebService) {

    val observations: LiveData<List<Observation>> = observationDAO.readAll()
    //lateinit var weather: WeatherWebEntity
    var weather: MutableLiveData<WeatherWebEntity> = MutableLiveData()

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

    fun getWeatherInfo() {
        val geoCode = "vienna,at"
        val apiKey = "d5728be66b249b5ad501e60868036f1f"
        weatherWebService.getWeatherInfo(geoCode, apiKey).enqueue(object: Callback<WeatherWebEntity> {
            override fun onResponse(
                call: Call<WeatherWebEntity>,
                response: Response<WeatherWebEntity>
            ) {
                weather = MutableLiveData(response.body()!!)
                Log.i("Response", response.toString())
                Log.i("Response.body", response.body().toString())
                /*
                val contacts = response.body()
                val databaseContacts = contacts?.map {
                    Contact(it.name, it.telephoneNumber.toString(), it.age)
                }
                if (databaseContacts != null) {
                    database.getContactDao().create(databaseContacts)
                }
                 */
            }
            override fun onFailure(call: Call<WeatherWebEntity>, t: Throwable) {
                Log.e("HTTP", "Get weather info failed", t)
            }
        }
        )
    }
}