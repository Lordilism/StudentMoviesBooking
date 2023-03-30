package com.example.moviesbookingapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.example.moviesbookingapp.data.vos.OtpVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class OtpTypeConverter {
    @TypeConverter
    fun toString(otp: OtpVO?): String {
        return Gson().toJson(otp)
    }

    @TypeConverter

    fun toOtpVo(str: String):OtpVO?{
        val otpType = object :TypeToken<OtpVO?>(){}.type
        return Gson().fromJson(str,otpType)
    }
}