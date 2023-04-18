package com.example.moviesbookingapp.data.vos

import com.google.gson.annotations.SerializedName

data class CheckOutBody(
    @SerializedName("movie_id")
    val movieId: Int?,

    @SerializedName("booking_date")
    val bookingDate: String?,

    @SerializedName("cinema_day_timeslot_id")
    val cinemaDayTimeslotId: Int?,

    @SerializedName("seat_number")
    val seatNumber: String?,

    @SerializedName("snacks")
    val snacks: MutableList<CheckOutBodySnack>?,

    @SerializedName("payment_type_id")
    val paymentTypeId: Int?,
) {
}