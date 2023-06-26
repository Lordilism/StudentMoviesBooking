package com.example.moviesbookingapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesbookingapp.R
import com.example.moviesbookingapp.data.vos.TicketForDatabase
import com.example.moviesbookingapp.delegates.TicketDelegate
import com.example.moviesbookingapp.viewholders.TicketTabViewHolder

class TicketTabAdapter(val ticketDelegate: TicketDelegate):
    RecyclerView.Adapter<TicketTabViewHolder>() {

    private var mTickets:List<TicketForDatabase> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketTabViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_ticket_tab,parent,false)
        return TicketTabViewHolder(view,ticketDelegate)
    }

    override fun onBindViewHolder(holder: TicketTabViewHolder, position: Int) {
        holder.bindData(mTickets[position])

    }

    override fun getItemCount(): Int {
        return mTickets.count()
    }

    fun setNewData(tickets: List<TicketForDatabase>?) {
        mTickets = tickets?: listOf()
        notifyDataSetChanged()

    }

}
