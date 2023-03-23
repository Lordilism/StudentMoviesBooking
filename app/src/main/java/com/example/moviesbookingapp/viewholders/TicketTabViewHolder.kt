package com.example.moviesbookingapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesbookingapp.delegates.TicketDelegate

class TicketTabViewHolder(itemView: View, ticketDelegate: TicketDelegate): RecyclerView.ViewHolder(itemView) {
    init {
        itemView.setOnClickListener {
            ticketDelegate.onTapTicket()
        }
    }
}