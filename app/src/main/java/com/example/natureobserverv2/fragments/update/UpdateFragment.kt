package com.example.natureobserverv2.fragments.update

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.natureobserverv2.R
import com.example.natureobserverv2.data.Observation
import com.example.natureobserverv2.fragments.add.AddViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.etDate
import kotlinx.android.synthetic.main.fragment_add.view.*
import kotlinx.android.synthetic.main.fragment_add.view.updateBtn
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*
import java.util.*

class UpdateFragment : Fragment(), View.OnClickListener {
    private val args by navArgs<UpdateFragmentArgs>()
    private val viewModel: UpdateViewModel by viewModels()
    // Instantiate new Calendar for etDate-View-Element
    private var calendar = Calendar.getInstance()
    // variable dateListener (function), defined later
    private lateinit var dateSetListener: DatePickerDialog.OnDateSetListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        // Load Args and set input values for EditText views
        view.updateEtTitle.setText(args.currentObservation.title)
        view.updateEtDate.setText(args.currentObservation.date)
        view.updateEtLocation.setText(args.currentObservation.location)
        view.updateEtNotes.setText(args.currentObservation.notes)

        view.updateBtn.setOnClickListener {
            updateObservationToDatabase()
        }

        // Add custom menu
        setHasOptionsMenu(true)

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
        updateEtDate.setOnClickListener(this)
    }

    // Validate input fields
    private fun inputCheck(title: String, date: String, location: String, notes: String): Boolean {
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(date) && TextUtils.isEmpty(location) && TextUtils.isEmpty(notes))
    }

    // Set text in view for etDate in specified format
    private fun updateDateInView(){
        val myFormat = "dd.MM.yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.getDefault())
        updateEtDate.setText(sdf.format(calendar.time).toString())
    }

    // If clicked view is etDate start a DatePickerDialog
    override fun onClick(v: View?) {
        when (v!!.id){
            R.id.updateEtDate -> {
                DatePickerDialog(requireContext(), dateSetListener,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)).show()
            }
        }
    }

    // Set custom menu layout
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    // Add custom menu selected item functions
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menuDelete){
            deleteObservation()
        }
        return super.onOptionsItemSelected(item)
    }

    // Update observation to database
    private fun updateObservationToDatabase() {
        val title = updateEtTitle.text.toString()
        val date = updateEtDate.text.toString()
        val location = updateEtLocation.text.toString()
        val notes = updateEtNotes.text.toString()

        if (inputCheck(title, date, location, notes)){
            // Create observation object
            val observation = Observation(args.currentObservation.id, title, date, location, notes)
            // Add observation to database
            this.viewModel.updateObservation(observation)
            Toast.makeText(requireContext(), getString(R.string.updatedToastUpdateObservation), Toast.LENGTH_LONG).show()
            // Navigate back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), getString(R.string.updateErrorToastUpdateObservation), Toast.LENGTH_LONG).show()
        }
    }

    // Delete observation from database
    private fun deleteObservation(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(getString(R.string.deleteMenuTitle) + " " + args.currentObservation.title)
        builder.setMessage(getString(R.string.deleteMenuMessage))
        builder.setPositiveButton(getString(R.string.deleteMenuTextYes)) { _,_ ->
            viewModel.deleteObservation(args.currentObservation)
            Toast.makeText(requireContext(), getString(R.string.deleteObservationConfirmationToast) + ": ${args.currentObservation.title}", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton(getString(R.string.deleteMenuTextNo)) { _,_ ->   }
        builder.create().show()
    }

}