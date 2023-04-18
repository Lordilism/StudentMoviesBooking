package com.example.moviesbookingapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.example.moviesbookingapp.data.vos.TicketCheckOutVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TicketForDatabaseTypeConverter {
    @TypeConverter
    fun toString(ticket: TicketCheckOutVO?) :String {
        return Gson().toJson(ticket)
    }

    @TypeConverter
    fun toTicketInformation(jsonString:String) : TicketCheckOutVO? {
        val ticketType = object : TypeToken<TicketCheckOutVO?>(){}.type
        return Gson().fromJson(jsonString,ticketType)
    }
}