package com.example.natureobserverv2.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.natureobserverv2.R
import com.example.natureobserverv2.data.Observation
import kotlinx.android.synthetic.main.fragment_list.view.*
import kotlinx.android.synthetic.main.fragment_update.*

class ListFragment : Fragment() {
    private val viewModel: ListViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        // Click Listener for FAB-Button (add new observation)
        view.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
        
        // RecyclerView
        val adapter = ListAdapter()
        val recyclerView = view.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.readObservations().observe(viewLifecycleOwner) {
            adapter.setData(it)
        }

        // Add custom menu
        setHasOptionsMenu(true)

        return view
    }

    // Set custom menu layout
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    // Add custom menu selected item functions
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menuDelete){
            deleteAllObservations()
        }
        return super.onOptionsItemSelected(item)
    }

    // Delete observation from database
    private fun deleteAllObservations(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(getString(R.string.listDeleteMenuTitle))
        builder.setMessage(getString(R.string.listDeleteMenuMessage))
        builder.setPositiveButton(getString(R.string.listDeleteMenuTextYes)) { _,_ ->
            viewModel.deleteAllObservations()
            Toast.makeText(requireContext(), getString(R.string.listDeleteObservationsConfirmationToast), Toast.LENGTH_LONG).show()
        }
        builder.setNegativeButton(getString(R.string.listDeleteMenuTextNo)) { _,_ ->   }
        builder.create().show()
    }

}