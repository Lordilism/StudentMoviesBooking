package com.example.moviesbookingapp.network.responses

import com.example.moviesbookingapp.data.vos.CheckOutBody
import com.example.moviesbookingapp.data.vos.CinemaInfoVO
import com.example.moviesbookingapp.utils.*
import retrofit2.Call
import retrofit2.http.*

interface TheMovieApi {
    @FormUrlEncoded
    @POST(API_GET_OTP)
    fun getOTP(
        @Field("phone")
        phone: String
    ): Call<OtpResponse>

    //SIGNIN WITH OTP
    @FormUrlEncoded
    @POST(API_SIGN_IN_OTP)
    fun signInOtp(
        @Field("phone")
        phone: String,
        @Field("otp")
        otp: String
    ): Call<OtpResponse>

    @GET(API_GET_CITIES)
    fun getCities(): Call<CityResponse>


    @GET(API_BANNER)
    fun getBanner(): Call<BannerResponse>

    @GET(API_MOVIES)
    fun getNowPlayingMovies(
        @Query("status")
        status: String = "current"
    ): Call<MovieResponse>

    @GET(API_MOVIES)
    fun getComingMovies(
        @Query("status")
        status: String = "comingsoon"
    ): Call<MovieResponse>

    @GET("$API_MOVIES_DETAILS/{movieId}")
    fun getMovieDetails(
        @Path("movieId") movieId: String
    ): Call<MovieDetailsResponse>

    @GET(API_CINEMA_TIMESLOT)
    fun getCinemaTimeslot(
        @Header("Authorization")
        authorization: String,
        @Query("date")
        date: String
    ): Call<CinemaTimeSlotResponse>

    @GET(API_CONFIG)
    fun getConfig(
    ): Call<ConfigResponse>

    @GET(API_GET_SEAT)
    fun getSeat(
        @Header("Authorization")
        authorization: String,
        @Query("cinema_day_timeslot_id") cinemaDayTimeSLotId: Int,
        @Query("booking_date") bookingDate: String
    ): Call<CinemaGetSeatResponse>

    @GET(API_GET_SNACK)
    fun getSnack(
        @Header("Authorization")
        authorization: String,
        @Query("category_id")
        categoryId: Int
    ): Call<SnackResponse>

    @GET(API_GET_SNACK_CATEGORY)
    fun getSnackCategory(
        @Header("Authorization")
        authorization: String,
    ):Call<SnackCategoryResponse>

    @GET(API_GET_PAYMENT_TYPE)
    fun getPayement(
        @Header("Authorization")
        authorization: String
    ):Call<PaymentTypeResponse>


    @POST(API_POST_CHECK_OUT)
    fun getCheckOut(
        @Header("Authorization")
        authorization: String,
        @Body checkOutTicket: CheckOutBody

    ):Call<CheckOutResponse>


    @GET(API_GET_CINEMA_INFO)
    fun getCinemaInfo(
    ):Call<CinemaResponse>

    @POST(API_LOG_OUT)
    fun logOut(
        @Header("Authorization")
        authorization: String
    ):Call<LogOutResponse>


}