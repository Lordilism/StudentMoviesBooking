package com.example.moviesbookingapp.data.models

import com.example.moviesbookingapp.data.vos.*
import com.example.moviesbookingapp.network.responses.OtpResponse

interface MovieModel {
    fun getOtp(
        onSuccess: (OtpResponse) -> Unit,
        onFailure: (String) -> Unit,
        phoneNo: String
    )

    fun signInOTP(
        onSuccess: (OtpResponse) -> Unit,
        onFailure: (String) -> Unit,
        phoneNo: String,
        otp: String
    )

    fun getOtp(code:Int):OtpResponse?

    fun insertCities(
        onSuccess: (List<CityVo>) -> Unit,
        onFailure: (String) -> Unit,

        )

    fun getCities():List<CityVo>?

    fun getBanner(
        onSuccess: (List<BannerVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getNowPlaingMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getComingSoon(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getMoviesDetails(
        onSuccess: (MovieVO) -> Unit,
        onFailure: (String) -> Unit,
        movieId: String
    )

    fun getCinemaTimeslots(
        authorization: String,
        date : String,
        onSuccess: (List<CinemaVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun insertConfig(
        onSuccess: (List<ConfigVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getConfig(key:String):ConfigVO?
}