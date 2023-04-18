package com.example.moviesbookingapp.network.responses

import com.example.moviesbookingapp.data.vos.SeatVO
import com.google.gson.annotations.SerializedName

data class CinemaGetSeatResponse(
    @SerializedName("code")
    val code: Int?,

    @SerializedName("message")
    val message:String?,

    @SerializedName("data")
    val data: MutableList<MutableList<SeatVO>>
) {
}