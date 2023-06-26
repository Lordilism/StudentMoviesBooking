package com.example.moviesbookingapp.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.moviesbookingapp.data.vos.*
import com.example.moviesbookingapp.network.responses.BannerResponse
import com.example.moviesbookingapp.network.responses.OtpResponse
import com.example.moviesbookingapp.persistence.daos.Daos

@Database(entities = [OtpResponse::class, CityVo::class, ConfigVO::class,MovieVO::class, BannerVO::class,TicketForDatabase::class], version = 6, exportSchema = false)
abstract class BookingDatabase:RoomDatabase() {
    companion object{
        const val BOOKING_DB = "BOOKING_DB"

        var dbInstance: BookingDatabase? = null

        fun getDBInstance(context: Context):BookingDatabase?{
            when(dbInstance){
                null->{
                    dbInstance = Room.databaseBuilder(context,BookingDatabase::class.java, BOOKING_DB)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return dbInstance
        }
    }

    abstract fun bookingDao():Daos
}