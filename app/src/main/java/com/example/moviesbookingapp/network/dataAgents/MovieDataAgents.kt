package com.example.moviesbookingapp.network.dataAgents

import com.example.moviesbookingapp.data.vos.*
import com.example.moviesbookingapp.network.responses.CityResponse
import com.example.moviesbookingapp.network.responses.OtpResponse

interface MovieDataAgents {
    fun getOtp(
        onSuccess: (OtpResponse) -> Unit,
        onFailure: (String)->Unit,
        phoneNo:String
    )

    fun signInOTP(
        onSuccess: (OtpResponse) -> Unit,
        onFailure: (String) -> Unit,
        phoneNo: String,
        otp:String
    )

    fun getCities(
        onSuccess: (List<CityVo>) -> Unit,
        onFailure: (String) -> Unit

    )

    fun getBanner(
        onSuccess: (List<BannerVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getNowPlayingMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getComingSoonMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getMovieDetails(
        onSuccess: (MovieVO) -> Unit,
        onFailure: (String) -> Unit,
        movieId:String
    )

    fun getCinemaTimeslots(
        authorization: String,
        date : String,
        onSuccess: (List<CinemaVO>) -> Unit,
        onFailure: (String) -> Unit
    )
}