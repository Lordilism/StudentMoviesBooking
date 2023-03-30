package com.example.moviesbookingapp.data.vos

import com.google.gson.annotations.SerializedName
import retrofit2.http.FormUrlEncoded

data class OtpVO(
    @SerializedName("code")
    val code:Int?,

    @SerializedName("message")
    val message:String?,

    @SerializedName("id")
    val id:Int?,

    @SerializedName("name")
    val name:String?,

    @SerializedName("email")
    val email:String?,

    @SerializedName("phone")
    val phone:Long?,

    @SerializedName("total_expense")
    val total_expense:Int?,

    @SerializedName("profile_image")
    val profile_image:String?






)