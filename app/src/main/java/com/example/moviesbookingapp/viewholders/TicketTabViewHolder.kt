package com.example.moviesbookingapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesbookingapp.data.vos.TicketForDatabase
import com.example.moviesbookingapp.delegates.TicketDelegate
import com.example.moviesbookingapp.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.viewholder_ticket_tab.view.*

class TicketTabViewHolder(itemView: View, ticketDelegate: TicketDelegate) :
    RecyclerView.ViewHolder(itemView) {
    private var mTicketForDatabase:TicketForDatabase? = null
    fun bindData(ticketForDatabase: TicketForDatabase) {
        mTicketForDatabase = ticketForDatabase

        itemView.tvMoviesName.text = ticketForDatabase.movieName

        itemView.tvCinemaTitle.text = ticketForDatabase.ticketCheckout?.bookingNo?.replace(Regex("[0-9-]"),"")
        Glide.with(itemView.context)
            .load("$IMAGE_BASE_URL${ticketForDatabase.moviePoster}")
            .into(itemView.ivLogoMovies)
        itemView.tvTicketAmount.text = ticketForDatabase.ticketCheckout?.totalSeat.toString()
        itemView.tvShowTimeForTicketFrag.text =
            ticketForDatabase.ticketCheckout?.timeslot?.startTime
        itemView.tvBookingDate.text = ticketForDatabase.ticketCheckout?.bookingDate
        itemView.tvSeatNumbers.text = ticketForDatabase.ticketCheckout?.seat

    }

    init {
        itemView.setOnClickListener {
            ticketDelegate.onTapTicket(mTicketForDatabase!!)
        }
    }
}