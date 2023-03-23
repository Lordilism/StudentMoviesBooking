package com.example.moviesbookingapp.network.responses

import com.example.moviesbookingapp.data.vos.BannerVO
import com.example.moviesbookingapp.data.vos.COMING_SOON
import com.example.moviesbookingapp.data.vos.MovieVO
import com.example.moviesbookingapp.data.vos.NOW_PLAYING
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
    fun getCities():Call<CityResponse>


    @GET(API_BANNER)
    fun getBanner():Call<BannerResponse>

    @GET(API_MOVIES)
    fun getNowPlayingMovies(
        @Query("status")
        status:String = "current"
    ):Call<MovieResponse>

    @GET(API_MOVIES)
    fun getComingMovies(
        @Query("status")
        status:String = "comingsoon"
    ):Call<MovieResponse>

    @GET("$API_MOVIES_DETAILS/{movieId}")
    fun getMovieDetails(
        @Path("movieId") movieId :String

    ):Call<MovieDetailsResponse>

    @GET(API_CINEMA_TIMESLOT)
    fun getCinemaTimeslot(
        @Header("Authorization")
        authorization: String,
        @Query("date")
        date: String
    ):Call<CinemaTimeSlotResponse>






}