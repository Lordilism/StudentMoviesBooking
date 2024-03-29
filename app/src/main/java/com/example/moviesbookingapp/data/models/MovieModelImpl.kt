package com.example.moviesbookingapp.data.models

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.moviesbookingapp.data.vos.*
import com.example.moviesbookingapp.network.dataAgents.MovieDataAgents
import com.example.moviesbookingapp.network.dataAgents.RetrofitDataAgentImpl
import com.example.moviesbookingapp.network.responses.LogOutResponse
import com.example.moviesbookingapp.network.responses.OtpResponse
import com.example.moviesbookingapp.persistence.BookingDatabase
import com.google.gson.Gson

object MovieModelImpl : MovieModel {
    private var mDatabase: BookingDatabase? = null

    private val mMovieDataAgents: MovieDataAgents = RetrofitDataAgentImpl

    private var mMovieVO: MovieVO? = null

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
                for (movies in it) {
                    movies.type = NOW_PLAYING
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
//                it.type = NOW_PLAYING
                it.type = mMovieVO?.type.toString()
                mMovieVO = it
                mDatabase?.bookingDao()?.insertSingleMovies(it)
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

    override fun getSeat(
        authorization: String,
        dayTimeSlotId: Int,
        bookingDate: String,
        onSuccess: (MutableList<MutableList<SeatVO>>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieDataAgents.getSeat(
            authorization = authorization,
            dayTimeSlotId = dayTimeSlotId,
            bookingDate = bookingDate,
            onSuccess = onSuccess,
            onFailure
        )
    }

    override fun getSnack(
        authorization: String,
        categoryId: Int,
        onSuccess: (List<SnackVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieDataAgents.getSnack(
            authorization,
            categoryId,
            onSuccess,
            onFailure
        )
    }

    override fun getSnackCategory(
        authorization: String,
        onSuccess: (List<SnackCategoryVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieDataAgents.getSnackCategory(authorization, onSuccess, onFailure)
    }

    override fun getPayment(
        authorization: String,
        onSuccess: (List<PaymentVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieDataAgents.getPayment(
            authorization,
            onSuccess,
            onFailure
        )
    }

    override fun getTicketCheckout(
        authorization: String,
        ticketCheckout: CheckOutBody,
        onSuccess: (TicketCheckOutVO) -> Unit,
        onFailure: (String) -> Unit
    ) {

        mMovieDataAgents.getTicketCheckout(authorization,ticketCheckout , onSuccess = {
            onSuccess(it)
        }, onFailure)
    }

    override fun insertTicket(ticket: TicketForDatabase) {
        mDatabase?.bookingDao()?.insertTicket(ticket)
    }

    override fun getAllTickets(): LiveData<List<TicketForDatabase>>? {
        return mDatabase?.bookingDao()?.getAllTickets()
    }

    override fun deleteTickets(ticketId: Int) {
        mDatabase?.bookingDao()?.deleteTicket(ticketId)
    }

    override fun postCheckOut() {

    }

    override fun getMoviefromDatabase(movieId: String):MovieVO? {
        return mDatabase?.bookingDao()?.getMoviesById(movieId.toInt())
    }

    override fun getCinemaInfo(
        onSuccess: (List<CinemaInfoVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mMovieDataAgents.getCinemaInfo(onSuccess,onFailure)
    }

    override fun getNowPlaingMoviesFromDatabase(): List<MovieVO>? {
        return mDatabase?.bookingDao()?.getMoviesByType(NOW_PLAYING)
    }

    override fun getComingSoonMoviesFromDatabase(): List<MovieVO>? {
        return mDatabase?.bookingDao()?.getMoviesByType(COMING_SOON)
    }

    override fun logOut(authorization: String,onSuccess:(LogOutResponse)->Unit, onFailure: (String) -> Unit) {

        mMovieDataAgents.logOut(authorization,onSuccess,onFailure)
    }


}