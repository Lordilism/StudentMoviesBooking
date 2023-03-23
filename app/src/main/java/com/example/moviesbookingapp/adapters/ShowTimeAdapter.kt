package com.example.moviesbookingapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesbookingapp.R
import com.example.moviesbookingapp.delegates.DateDelegate
import com.example.moviesbookingapp.viewholders.ShowTimeViewHolder

class ShowTimeAdapter(private val delegate: DateDelegate): RecyclerView.Adapter<ShowTimeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowTimeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_cinema_date,parent,false)
        return ShowTimeViewHolder(view,delegate)
    }

    override fun onBindViewHolder(holder: ShowTimeViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return 4
    }
}