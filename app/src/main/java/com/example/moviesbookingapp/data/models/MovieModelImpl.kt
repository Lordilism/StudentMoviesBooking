package com.example.moviesbookingapp.data.models

import android.content.Context
import com.example.moviesbookingapp.data.vos.*
import com.example.moviesbookingapp.network.dataAgents.MovieDataAgents
import com.example.moviesbookingapp.network.dataAgents.RetrofitDataAgentImpl
import com.example.moviesbookingapp.network.responses.OtpResponse
import com.example.moviesbookingapp.persistence.BookingDatabase

object MovieModelImpl : MovieModel {
    private var mDatabase: BookingDatabase? = null

    private val mMovieDataAgents: MovieDataAgents = RetrofitDataAgentImpl

    fun initDatabase(context: Context) {
        mDatabase = BookingDatabase.getDBInstance(context)
    }

    override fun getOtp(
        onSuccess: (OtpResponse) -> Unit,
        onFailure: (String) -> Unit,
        phoneNo: String
    ) {
        mMovieDataAgents.getOtp(
            onSuccess = onSuccess,
            onFailure = onFailure,
            phoneNo = phoneNo

        )
    }

    override fun getOtp(code: Int): OtpResponse? {
        return mDatabase?.bookingDao()?.getInformation(code)
    }

    override fun signInOTP(
        onSuccess: (OtpResponse) -> Unit,
        onFailure: (String) -> Unit,
        phoneNo: String,
        otp: String
    ) {
        //Database

        //Network
        mMovieDataAgents.signInOTP(
            onSuccess = {
                mDatabase?.bookingDao()?.insertInformation(it)
                onSuccess(it)
            },
            onFailure = onFailure,
            phoneNo = phoneNo,
            otp = otp

        )
    }

    override fun insertCities(onSuccess: (List<CityVo>) -> Unit, onFailure: (String) -> Unit) {
        mMovieDataAgents.getCities(
            onSuccess = {
                mDatabase?.bookingDao()?.insertCities(it)
                onSuccess(it)
            },
            onFailure = onFailure
        )
    }

    override fun getCities(): List<CityVo>? {
        return mDatabase?.bookingDao()?.getCities()
    }

    override fun getBanner(onSuccess: (List<BannerVO>) -> Unit, onFailure: (String) -> Unit) {

        onSuccess(mDatabase?.bookingDao()?.getBanner() ?: listOf())

        mMovieDataAgents.getBanner(
            onSuccess = {
                mDatabase?.bookingDao()?.insertBanners(it)
                onSuccess(it)
            },
            onFailure = onFailure
        )
    }

    override fun getNowPlaingMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit,

        ) {
        //DataBase
        onSuccess(mDatabase?.bookingDao()?.getMoviesByType(NOW_PLAYING) ?: listOf())

        mMovieDataAgents.getNowPlayingMovies(
            onSuccess = {
                for (nowShowing in it) {
                    nowShowing.type = NOW_PLAYING
                }
                mDatabase?.bookingDao()?.insertMovies(it)
                onSuccess(it)
            },
            onFailure = onFailure,

            )
    }

    override fun getComingSoon(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit,

        ) {
        //DataBase
        onSuccess(mDatabase?.bookingDao()?.getMoviesByType(COMING_SOON) ?: listOf())

        mMovieDataAgents.getComingSoonMovies(
            onSuccess = {
                for (comingSoon in it) {
                    comingSoon.type = COMING_SOON
                }
                mDatabase?.bookingDao()?.insertMovies(it)
                onSuccess(it)

            },
            onFailure = {
                onFailure(it)
            }
        )
    }

    override fun getMoviesDetails(
        onSuccess: (MovieVO) -> Unit,
        onFailure: (String) -> Unit,
        movieId: String
    ) {
        mDatabase?.bookingDao()?.getMoviesById(movieId.toInt())?.let { onSuccess(it) }
        mMovieDataAgents.getMovieDetails(
            onSuccess = {
                mDatabase?.bookingDao()?.getMoviesById(movieId.toInt())
                onSuccess(it)
            },
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

    override fun insertConfig(onSuccess: (List<ConfigVO>) -> Unit, onFailure: (String) -> Unit) {
        mMovieDataAgents.getConfig(
            onSuccess = {
                mDatabase?.bookingDao()?.insertConfig(it)
                onSuccess(it)
            },
            onFailure = onFailure
        )
    }

    override fun getConfig(key: String): ConfigVO? {
        return mDatabase?.bookingDao()?.getConfig(key)
    }


}