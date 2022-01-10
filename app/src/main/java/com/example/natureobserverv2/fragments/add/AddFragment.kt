package com.example.natureobserverv2.fragments.add

import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.natureobserverv2.R
import com.example.natureobserverv2.data.Observation
import com.example.natureobserverv2.data.ObservationViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*
import java.util.*

class AddFragment : Fragment(), View.OnClickListener {

    private val viewModel: AddViewModel by viewModels()
    // Instantiate new Calendar for etDate-View-Element
    private var calendar = Calendar.getInstance()
    // variable dateListener (function), defined later
    private lateinit var dateSetListener: DatePickerDialog.OnDateSetListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        view.addBtn.setOnClickListener {
            insertObservationToDatabase()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // DatePicker-Listener set Date to chosen Date and update View in etDate when date selected
        dateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateDateInView()
        }

        // Set Clicklistener for etDate-Textview, inherits ViewClicklistener from main view (class NewObservationActivity)
        etDate.setOnClickListener(this)
    }

    private fun insertObservationToDatabase() {
        val title = etTitle.text.toString()
        val date = etDate.text.toString()
        val location = etLocation.text.toString()
        val notes = etNotes.text.toString()

        if (inputCheck(title, date, location, notes)){
            // Create observation object
            val observation = Observation(0, title, date, location, notes)
            // Add observation to database
            this.viewModel.addObservation(observation)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
            // Navigate back
            findNavController().navigate(R.id.listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(title: String, date: String, location: String, notes: String): Boolean {
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(date) && TextUtils.isEmpty(location) && TextUtils.isEmpty(notes))
    }

    // Set text in view for etDate in specified format
    private fun updateDateInView(){
        val myFormat = "dd.MM.yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.getDefault())
        etDate.setText(sdf.format(calendar.time).toString())
    }

    // If clicked view is etDate start a DatePickerDialog
    override fun onClick(v: View?) {
        when (v!!.id){
            R.id.etDate -> {
                DatePickerDialog(requireContext(), dateSetListener,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)).show()
            }
        }
    }


}