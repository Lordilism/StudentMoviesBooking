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

   fun getConfig(
       onSuccess: (List<ConfigVO>) -> Unit,
       onFailure: (String) -> Unit
   )

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
}