package com.example.natureobserverv2.fragments.weather

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.natureobserverv2.R
import com.example.natureobserverv2.data.repository
import com.example.natureobserverv2.fragments.update.UpdateViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import kotlinx.android.synthetic.main.fragment_weather.*
import kotlinx.android.synthetic.main.fragment_weather.view.*


class WeatherFragment : Fragment() {
    private val viewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_weather, container, false)

        // Click Listener for Forecast-Button
        view.showForecastBtn.setOnClickListener {
            val city = etWeatherLocation.text.toString()
            // run weatherWebService to get actual weather info
            viewModel.getWeatherInfo(city)
        }

        // Read out forecast data from weather-webservice-call
        viewModel.weather().observe(viewLifecycleOwner){
            Log.e("INFO", "Observer was invoked")
            if (it == null) {
                Toast.makeText(requireContext(), "Weather Request Status >= 400", Toast.LENGTH_LONG).show()
            } else {
                Log.e("it", it.toString())
                view.tvWeather.text = it.name
            }
            //tvWeather.text = it?.toString() ?: "Weather Request Status >= 400"
        }

        return view
    }


}