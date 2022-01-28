package com.example.natureobserverv2.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.natureobserverv2.web.*
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.util.ClassUtil
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
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

    fun anyData(): Observation? {
        val anyData = observationDAO.anyData()
        Log.e("isDbEmpty: ", anyData.toString())
        return anyData
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
                    // to keep all observers alive actualise MutableLiveData with .postValue()!!
                    weather.postValue(response.body())
                    Log.i("Response.body", response.body().toString())
                } else {
                    weatherError.postValue(response.code())
                    // statusCode: 401 -> invalid APIkey
                    // statusCode: 404 -> city not found
                    Log.e("WeatherWebService-Request-Error", response.raw().toString())
                    Log.e("Response.body", response.body().toString())

                    // Reparse response to ErrorWeatherWebEntity -> never used!
                    val mapper = jacksonObjectMapper()
                    val errorwebentity = mapper.readValue<ErrorWeatherWebEntity>(response.body().toString())
                }
            }
            override fun onFailure(call: Call<WeatherWebEntity>, t: Throwable) {
                Log.e("HTTP", "Get weather info failed", t)
            }
        }
        )
    }



    // Create observation-objects and return observationTestArray
    fun getObservationTestArray(): ArrayList<Observation>{
        var observationTestArray: ArrayList<Observation> = ArrayList()
        observationTestArray.add(Observation(0, "Buntspecht", "12.02.2021", "Wienerberg", "Schöner Vogel"))
        observationTestArray.add(Observation(0, "Spiegeleiqualle", "12.08.2019", "Creta", "Die schaut so lässig aus. Sie hat nur ein schwaches Nesselgift und ist für den Menschen harmlos."))
        observationTestArray.add(Observation(0, "Rochen", "25.07.2018", "Gran Canaria", "Gut getarnt"))
        observationTestArray.add(Observation(0, "Smaragdeidechse", "06.04.2021", "Perchtholdsdorf", "Wie die geflitzt ist"))
        observationTestArray.add(Observation(0, "Hirschkäfer", "07.05.2019", "Kahlenberg", "Wow, rießiger Käfer mit seinem Geweih"))
        observationTestArray.add(Observation(0, "Blindschleichen bei Paarung", "09.05.2021", "Schönwald bei Maria Gugging", "Brutal und ein bisschen verstörend der Anblick, dennoch interessant mal gesehen zu haben."))
        observationTestArray.add(Observation(0, "Fasan", "08.05.2021", "Leopoldsdorf", "Im hohen Gras gesichtet"))
        observationTestArray.add(Observation(0, "Europäische Sumpfschildkröte", "10.04.2021", "Lobau", "Bereits am Sonne tanken. Die Umgebungstemperatur der Eier bestimmt, ob sich aus diesen mehr Weibchen oder mehr Männchen entwickeln. Gefährdungsgrad: " +
                "vom Aussterben bedroht."))
        observationTestArray.add(Observation(0, "Star nistet gegenüber", "25.05.2021", "Home", "Immer fleißig auf Futtersuche für die kleinen Mäuler"))
        observationTestArray.add(Observation(0, "Stiglitz", "16.06.2021", "Home", "So tolle Farben bei genauerer Betrachtung"))
        return observationTestArray
    }

}