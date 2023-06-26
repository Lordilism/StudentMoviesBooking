package com.example.moviesbookingapp.data.vos

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CinemaInfoVO(
    @SerializedName("id")
    val id:Int?,

    @SerializedName("name")
    val name:String?,

    @SerializedName("phone")
    val phone:String?,

    @SerializedName("email")
    val email:String?,

    @SerializedName("address")
    val address:String?,

    @SerializedName("promo_vdo_url")
    val promoVdoUrl:String?,

    @SerializedName("facilities")
    val facilities:List<FacilitiesVO>?,

    @SerializedName("safety")
    val safety:List<String>?,


):Serializable {
}