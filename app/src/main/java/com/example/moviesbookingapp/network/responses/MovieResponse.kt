package com.example.moviesbookingapp.network.responses

import com.example.moviesbookingapp.data.vos.MovieVO
import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("code")
    val code:Int?,

    @SerializedName("message")
    val message:String?,

    @SerializedName("data")
    val data: List<MovieVO>?
) {
}