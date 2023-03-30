package com.example.moviesbookingapp

import android.app.Application
import com.example.moviesbookingapp.data.models.MovieModelImpl

class BookingApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        MovieModelImpl.initDatabase(applicationContext)
    }
}