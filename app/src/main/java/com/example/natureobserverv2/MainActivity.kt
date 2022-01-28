package com.example.natureobserverv2

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.natureobserverv2.data.ObservationDatabase
import com.example.natureobserverv2.data.ObservationRepository
import com.example.natureobserverv2.data.repository
import com.example.natureobserverv2.web.createWebService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
        // load ObservationTestArray to Database when no entries yet
        lifecycleScope.launch(Dispatchers.IO){
            if (repository.anyData() == null){
                val observationTestArray = repository.getObservationTestArray()
                for (observation in observationTestArray){
                        repository.addObservation(observation)
                }
            }
        }
        // setup actionbar for fragments
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val mainNavController = navHostFragment.navController
        // TODO: Farbe Actionbar in Theme ändern
        setupActionBarWithNavController(mainNavController)
        // TODO: better to initialize with self-created instance (dummy)
        // initialize weather variable with WeatherWebEntity -> not null anymore
        repository.getWeatherInfo("vienna")
    }

    // enable back-navigation with actionbar for fragments
    override fun onSupportNavigateUp(): Boolean {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val mainNavController = navHostFragment.navController
        return mainNavController.navigateUp() || super.onSupportNavigateUp()
    }

}