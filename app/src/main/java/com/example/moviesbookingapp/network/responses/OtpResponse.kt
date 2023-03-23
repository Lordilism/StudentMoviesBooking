package com.example.moviesbookingapp.network.responses

import com.example.moviesbookingapp.data.vos.OtpVO
import com.google.gson.annotations.SerializedName

data class OtpResponse(

    @SerializedName("code")
    val code:Int?,

    @SerializedName("message")
    val message:String?,

    @SerializedName("token")
    val token:String?,

    @SerializedName("data")
    val data:OtpVO?



) {
}