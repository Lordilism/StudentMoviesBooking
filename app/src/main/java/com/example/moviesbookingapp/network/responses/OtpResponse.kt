package com.example.moviesbookingapp.network.responses

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.moviesbookingapp.data.vos.OtpVO
import com.example.moviesbookingapp.persistence.typeconverters.OtpTypeConverter
import com.google.gson.annotations.SerializedName

@Entity(tableName = "otp")
@TypeConverters(
    OtpTypeConverter::class
)
data class OtpResponse(

    @SerializedName("code")
    @PrimaryKey
    val code: Int=0,

    @SerializedName("message")
    @ColumnInfo(name = "message")
    val message: String?,

    @SerializedName("token")
    @ColumnInfo(name = "token")
    val token: String?,

    @SerializedName("data")
    @ColumnInfo(name = "data")
    val data: OtpVO?


) {
}