package com.example.natureobserverv2.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.natureobserverv2.R
import com.example.natureobserverv2.data.Observation
import kotlinx.android.synthetic.main.observation_row.view.*

class ListViewHolder(listItemRootView: View): RecyclerView.ViewHolder(listItemRootView)

class ListAdapter: RecyclerView.Adapter<ListViewHolder>() {

    private var observationList = emptyList<Observation>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.observation_row, parent, false))
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val currentListItem = observationList[position]
        holder.itemView.tvListDate.text = currentListItem.date.toString()
        holder.itemView.tvListTitle.text = currentListItem.title.toString()
        holder.itemView.tvListLocation.text = currentListItem.location.toString()
        holder.itemView.tvListNotes.text = currentListItem.notes.toString()
    }

    override fun getItemCount(): Int {
        return observationList.size
    }

    fun setData(observations: List<Observation>){
        this.observationList = observations
        notifyDataSetChanged()
    }
}