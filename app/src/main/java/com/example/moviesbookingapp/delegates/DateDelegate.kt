package com.example.moviesbookingapp.delegates

interface DateDelegate {
    fun onTapDate(cinemaTimeSlotId: Int, bookingDate: String, startTime: String?)
    fun onTapDetails()
}