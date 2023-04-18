package com.example.moviesbookingapp.data.vos

import com.google.gson.annotations.SerializedName

data class CheckOutBodySnack(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("quantity")
    val quantity: Int?
) {
}