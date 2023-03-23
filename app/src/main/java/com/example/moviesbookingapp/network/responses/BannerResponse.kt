package com.example.moviesbookingapp.network.responses

import com.example.moviesbookingapp.data.vos.BannerVO
import com.google.gson.annotations.SerializedName

data class BannerResponse(
    @SerializedName("code")
    val code:Int?,

    @SerializedName("message")
    val message:String?,

    @SerializedName("data")
    val data: List<BannerVO>?
) {
}