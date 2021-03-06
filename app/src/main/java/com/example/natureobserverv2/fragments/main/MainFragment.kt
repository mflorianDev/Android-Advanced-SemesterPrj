package com.example.natureobserverv2.fragments.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.natureobserverv2.R
import com.example.natureobserverv2.data.repository
import kotlinx.android.synthetic.main.fragment_list.view.*
import kotlinx.android.synthetic.main.fragment_main.view.*


class MainFragment : Fragment() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        view.showAllObservationsBtn.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_listFragment)
        }
        view.showWeatherBtn.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_weatherFragment)
        }

        return view
    }

}