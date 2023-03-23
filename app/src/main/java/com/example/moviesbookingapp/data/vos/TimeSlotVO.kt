package com.example.moviesbookingapp.data.vos

import com.google.gson.annotations.SerializedName

data class TimeSlotVO(
    @SerializedName("cinema_day_timeslot_id")
    val cinemaDayTimeslotId :Int?,

    @SerializedName("start_time")
    val startTime: String?,

    @SerializedName("status")
    val status : Int?
) {

}
