package com.example.moviesbookingapp.data.vos

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FacilitiesVO(
    @SerializedName("id")
    val id:Int?,

    @SerializedName("title")
    val title:String?,

    @SerializedName("img")
    val image:String?

):Serializable{
}