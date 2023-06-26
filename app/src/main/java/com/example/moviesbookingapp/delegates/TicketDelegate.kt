package com.example.moviesbookingapp.delegates

import com.example.moviesbookingapp.data.vos.TicketForDatabase

interface TicketDelegate {
    fun onTapTicket(mTicketForDatabase: TicketForDatabase)
}
