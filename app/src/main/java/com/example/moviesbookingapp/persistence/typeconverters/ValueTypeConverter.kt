package com.example.moviesbookingapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ValueTypeConverter {
    @TypeConverter
    fun toString(value: Any?):String{
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toConfigVO(str:String):Any?{
        val config = object :TypeToken<Any?>(){}.type
        return Gson().fromJson(str,config)
    }
}