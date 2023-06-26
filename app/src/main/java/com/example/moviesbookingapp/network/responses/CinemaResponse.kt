package com.example.moviesbookingapp.network.responses

import com.example.moviesbookingapp.data.vos.CinemaInfoVO
import com.google.gson.annotations.SerializedName

data class CinemaResponse (
    @SerializedName("code")
    val code:Int?,

    @SerializedName("message")
    val message:String?,

    @SerializedName("data")
    val data:List<CinemaInfoVO>,

    @SerializedName("latest_time")
    val latestTime:String?
        ){
}