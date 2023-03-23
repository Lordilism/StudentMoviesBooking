package com.example.moviesbookingapp.data.models

import com.example.moviesbookingapp.data.vos.*
import com.example.moviesbookingapp.network.dataAgents.MovieDataAgents
import com.example.moviesbookingapp.network.dataAgents.RetrofitDataAgentImpl
import com.example.moviesbookingapp.network.responses.OtpResponse

object MovieModelImpl: MovieModel {
    private val mMovieDataAgents:MovieDataAgents = RetrofitDataAgentImpl
    override fun getOtp(
        onSuccess: (OtpResponse) -> Unit,
        onFailure: (String) -> Unit,
        phoneNo: String
    ) {
        mMovieDataAgents.getOtp(
            onSuccess = onSuccess,
            onFailure = onFailure,
            phoneNo =phoneNo

        )
    }

    override fun signInOTP(
        onSuccess: (OtpResponse) -> Unit,
        onFailure: (String) -> Unit,
        phoneNo: String,
        otp: String
    ) {
        mMovieDataAgents.signInOTP(
            onSuccess = onSuccess,
            onFailure = onFailure,
            phoneNo = phoneNo,
            otp = otp

        )
    }

    override fun getCities(onSuccess: (List<CityVo>) -> Unit, onFailure: (String) -> Unit) {
        mMovieDataAgents.getCities(
            onSuccess= onSuccess,
            onFailure = onFailure
        )
    }

    override fun getBanner(onSuccess: (List<BannerVO>) -> Unit, onFailure: (String) -> Unit) {
        mMovieDataAgents.getBanner(
            onSuccess=onSuccess,
            onFailure =onFailure
        )
    }

    override fun getNowPlaingMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit,

    ) {
        mMovieDataAgents.getNowPlayingMovies(
            onSuccess={
                for (nowShowing in it){
                    nowShowing.type = NOW_PLAYING
                }
                onSuccess(it)
            },
            onFailure=onFailure,

        )
    }

    override fun getComingSoon(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit,

    ) {
        mMovieDataAgents.getComingSoonMovies(
            onSuccess={
                    for(comingSoon in it){
                        comingSoon.type = COMING_SOON
                    }
                onSuccess(it)

            },
            onFailure={
                onFailure(it)
            }
        )
    }

    override fun getMoviesDetails(
        onSuccess: (MovieVO) -> Unit,
        onFailure: (String) -> Unit,
        movieId: String
    ) {
        mMovieDataAgents.getMovieDetails(
            onSuccess = onSuccess,
            onFailure = onFailure,
            movieId
        )
    }

    override fun getCinemaTimeslots(
        authorization: String,
        date: String,
        onSuccess: (List<CinemaVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieDataAgents.getCinemaTimeslots(
            authorization,
            date,
            onSuccess = onSuccess,
            onFailure = onFailure
        )
    }


}