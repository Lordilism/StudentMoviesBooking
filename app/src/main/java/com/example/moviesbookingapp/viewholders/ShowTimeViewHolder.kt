package com.example.moviesbookingapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesbookingapp.delegates.DateDelegate

class ShowTimeViewHolder(itemView: View, val delegate: DateDelegate) :
    RecyclerView.ViewHolder(itemView) {
    init {
        itemView.setOnClickListener {

            delegate.onTapDate()

        }
    }
}