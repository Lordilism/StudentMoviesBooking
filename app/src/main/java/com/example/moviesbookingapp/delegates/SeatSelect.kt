package com.example.moviesbookingapp.delegates

import com.example.moviesbookingapp.data.vos.SeatVO

interface SeatSelect {
    fun onTapSeat(seatForTapping: SeatVO,isAddOrNot: Boolean)
}