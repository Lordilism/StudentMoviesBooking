package com.example.moviesbookingapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.example.moviesbookingapp.data.vos.TimeSlotColor
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TimeColorConverter {
    @TypeConverter
    fun toString(configTimeslot: List<TimeSlotColor>?) :String {
        return Gson().toJson(configTimeslot)
    }

    @TypeConverter
    fun toTimeslotColorVO(jsonString:String) : List<TimeSlotColor>? {
        val configTimeslotType = object : TypeToken<List<TimeSlotColor>?>(){}.type
        return Gson().fromJson(jsonString,configTimeslotType)
    }
}