package com.example.moviesbookingapp.network.responses

import com.google.gson.annotations.SerializedName

data class LogOutResponse(
    @SerializedName("code")
    val code:Int?,

    @SerializedName("message")
    val message:String?
) {


}