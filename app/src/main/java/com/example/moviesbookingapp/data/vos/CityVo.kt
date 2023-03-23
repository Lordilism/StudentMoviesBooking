package com.example.moviesbookingapp.data.vos

import com.google.gson.annotations.SerializedName

data class CityVo(
    @SerializedName("id")
    val id:Int?,

    @SerializedName("name")
    var name:String?,

    @SerializedName("created_at")
    val createdAt:String?,

    @SerializedName("updated_at")
    val updatedAt:String?
) {
}