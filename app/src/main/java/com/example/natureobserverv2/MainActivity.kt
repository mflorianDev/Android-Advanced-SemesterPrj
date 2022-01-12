package com.example.natureobserverv2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.natureobserverv2.data.ObservationDatabase
import com.example.natureobserverv2.data.ObservationRepository
import com.example.natureobserverv2.data.repository
import com.example.natureobserverv2.web.createWebService

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // initialize database
        val observationDAO = ObservationDatabase.getDatabase(application).observationDAO()
        // initialize weatherWebService
        val weatherWebService = createWebService()
        // initialize repository
        repository = ObservationRepository(observationDAO, weatherWebService)
        // TODO: delete getWeatherInfo call
        repository.getWeatherInfo()
        repository.weather.observe(this){
            Log.e("Observed Weather: ", it.toString())
        }

        // setup actionbar for fragments
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val mainNavController = navHostFragment.navController
        setupActionBarWithNavController(mainNavController)
    }

    // enable back-navigation with actionbar for fragments
    override fun onSupportNavigateUp(): Boolean {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val mainNavController = navHostFragment.navController
        return mainNavController.navigateUp() || super.onSupportNavigateUp()
    }
}