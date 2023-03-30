package com.example.moviesbookingapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GenreListTypeConverter {
    @TypeConverter
    fun toString(json: List<String>?):String{
        return Gson().toJson(json)

    }

    @TypeConverter
    fun toCollection(str:String):List<String>?{
        val collection = object :TypeToken<List<String>?>(){}.type
        return Gson().fromJson(str,collection)
    }
}