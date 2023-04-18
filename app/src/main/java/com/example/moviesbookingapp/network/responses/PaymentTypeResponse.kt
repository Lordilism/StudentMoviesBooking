package com.example.moviesbookingapp.network.responses

import com.example.moviesbookingapp.data.vos.PaymentVO
import com.google.gson.annotations.SerializedName

data class PaymentTypeResponse(
    @SerializedName("code")
    val code:Int?,

    @SerializedName("message")
    val message:String?,

    @SerializedName("data")
    val data:List<PaymentVO>?
) {


}