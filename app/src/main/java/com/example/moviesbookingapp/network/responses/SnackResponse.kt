package com.example.moviesbookingapp.network.responses

import com.example.moviesbookingapp.data.vos.SnackVO
import com.google.gson.annotations.SerializedName

data class SnackResponse(
        @SerializedName("code")
        val code:Int?,

        @SerializedName("message")
        val message:String?,

        @SerializedName("data")
        val data:List<SnackVO>?
) {
}