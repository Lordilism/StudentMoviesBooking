package com.example.moviesbookingapp.data.vos

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CheckOutBodySnack(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("quantity")
    var quantity: Int? = 1
):Serializable {
}