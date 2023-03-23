package com.example.moviesbookingapp.data.models

import com.example.moviesbookingapp.data.vos.BannerVO
import com.example.moviesbookingapp.data.vos.CinemaVO
import com.example.moviesbookingapp.data.vos.CityVo
import com.example.moviesbookingapp.data.vos.MovieVO
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

    fun getCities(
        onSuccess: (List<CityVo>) -> Unit,
        onFailure: (String) -> Unit,

        )

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
}