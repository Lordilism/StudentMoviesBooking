package com.example.moviesbookingapp.data.vos

import com.google.gson.annotations.SerializedName

data class SeatVO(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("price")
    var price: Int?,

    @SerializedName("seat_name")
    var seatName: String?,

    @SerializedName("symbol")
    val symbol: String?,

    @SerializedName("type")
    var type: String?,





) {
}