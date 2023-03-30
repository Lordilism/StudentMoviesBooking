package com.example.moviesbookingapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.example.moviesbookingapp.data.vos.CastersVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CasterVoListTypeConverter {
    @TypeConverter
    fun toString(json: List<CastersVO>?):String{
        return Gson().toJson(json)
    }

    @TypeConverter
    fun toCollectionVo(str:String):List<CastersVO>?{
        val collectionVO = object :TypeToken<List<CastersVO>?>(){}.type
        return Gson().fromJson(str,collectionVO)
    }
}