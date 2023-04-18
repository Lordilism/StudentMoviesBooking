package com.example.moviesbookingapp.network.responses

import com.example.moviesbookingapp.data.vos.TicketCheckOutVO
import com.google.gson.annotations.SerializedName

data class CheckOutResponse(

    @SerializedName("code")
    val code:Int?,

    @SerializedName("message")
    val message:String,

    @SerializedName("data")
    val data: TicketCheckOutVO?

) {
}