package com.example.moviesbookingapp.data.models

import androidx.lifecycle.LiveData
import com.example.moviesbookingapp.data.vos.*
import com.example.moviesbookingapp.network.responses.LogOutResponse
import com.example.moviesbookingapp.network.responses.OtpResponse
import com.example.moviesbookingapp.network.responses.SnackResponse

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

    fun getSeat(
        authorization:String,
        dayTimeSlotId:Int,
        bookingDate:String,
        onSuccess:(MutableList<MutableList<SeatVO>>) -> Unit,
        onFailure:(String) -> Unit
    )

    fun getSnack(
        authorization: String,
        categoryId:Int,
        onSuccess: (List<SnackVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getSnackCategory(
        authorization: String,
        onSuccess: (List<SnackCategoryVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getPayment(
        authorization: String,
        onSuccess: (List<PaymentVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getTicketCheckout(
        authorization:String,
        ticketCheckout: CheckOutBody,
        onSuccess:(TicketCheckOutVO) -> Unit,
        onFailure:(String) -> Unit
    )

    fun insertTicket(ticket:TicketForDatabase)

    fun getAllTickets(): LiveData<List<TicketForDatabase>>?

    fun deleteTickets(ticketId:Int)
    fun postCheckOut()

    fun getMoviefromDatabase(movieId: String): MovieVO?

    fun getCinemaInfo(
        onSuccess: (List<CinemaInfoVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getNowPlaingMoviesFromDatabase():List<MovieVO>?
    fun getComingSoonMoviesFromDatabase():List<MovieVO>?
    fun logOut(authorization: String,onSuccess: (LogOutResponse) -> Unit,onFailure: (String) -> Unit)
}