package com.example.moviesbookingapp.delegates

import com.example.moviesbookingapp.data.vos.CinemaInfoVO

interface DateDelegate {
    fun onTapDate(
        cinemaTimeSlotId: Int,
        bookingDate: String,
        startTime: String?,
        cinemaName: String?
    )
    fun onTapDetails(mCinemaInfoVO: CinemaInfoVO)
}