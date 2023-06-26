package com.example.moviesbookingapp.network.dataAgents

import com.example.moviesbookingapp.data.vos.*
import com.example.moviesbookingapp.network.responses.*
import com.example.moviesbookingapp.utils.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitDataAgentImpl : MovieDataAgents {
    private var mTheMovieApi: TheMovieApi? = null

    init {
        val mOkHttpClient = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(mOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        mTheMovieApi = retrofit.create(TheMovieApi::class.java)

    }


    override fun getOtp(
        onSuccess: (OtpResponse) -> Unit,
        onFailure: (String) -> Unit,
        phoneNo: String
    ) {
        mTheMovieApi?.getOTP(phoneNo)?.enqueue(object : Callback<OtpResponse> {
            override fun onResponse(call: Call<OtpResponse>, response: Response<OtpResponse>) {
                if (response.isSuccessful) {
                    response?.body()?.let {
                        onSuccess(it)
                    }
                } else {
                    onFailure(response.message())
                }
            }

            override fun onFailure(call: Call<OtpResponse>, t: Throwable) {
                onFailure(t.message ?: "")

            }

        })
    }

    override fun signInOTP(
        onSuccess: (OtpResponse) -> Unit,
        onFailure: (String) -> Unit,
        phoneNo: String,
        otp: String
    ) {
        mTheMovieApi?.signInOtp(phoneNo, otp)?.enqueue(object : Callback<OtpResponse> {
            override fun onResponse(call: Call<OtpResponse>, response: Response<OtpResponse>) {
                if (response.isSuccessful) {
                    response?.body()?.let {

                        onSuccess(it)

                    }
                } else {
                    onFailure(response.message())
                }
            }

            override fun onFailure(call: Call<OtpResponse>, t: Throwable) {
                onFailure(t.message ?: "")
            }

        })
    }

    override fun getCities(onSuccess: (List<CityVo>) -> Unit, onFailure: (String) -> Unit) {
        mTheMovieApi?.getCities()?.enqueue(object : Callback<CityResponse> {
            override fun onResponse(call: Call<CityResponse>, response: Response<CityResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        it.data?.let { cities -> onSuccess(cities) }
                    }
                } else {
                    onFailure(response.message())
                }
            }

            override fun onFailure(call: Call<CityResponse>, t: Throwable) {
                onFailure(t.message ?: "")
            }

        })
    }

    override fun getBanner(onSuccess: (List<BannerVO>) -> Unit, onFailure: (String) -> Unit) {
        mTheMovieApi?.getBanner()?.enqueue(object : Callback<BannerResponse> {
            override fun onResponse(
                call: Call<BannerResponse>,
                response: Response<BannerResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { it ->
                        it.data?.let { bannerData ->
                            onSuccess(bannerData)
                        }
                    }
                } else {
                    onFailure(response.message())
                }
            }

            override fun onFailure(call: Call<BannerResponse>, t: Throwable) {
                onFailure(t.message ?: "")
            }

        })
    }

    override fun getNowPlayingMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit,

        ) {
        mTheMovieApi?.getNowPlayingMovies()?.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        it.data.let { movies ->
                            if (movies != null) {
                                onSuccess(movies)
                            }
                        }
                    }
                } else {
                    onFailure(response.message())
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                onFailure(t.message ?: "")
            }

        })
    }

    override fun getComingSoonMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit,

        ) {
        mTheMovieApi?.getComingMovies()?.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        it.data.let { movies ->
                            if (movies != null) {
                                onSuccess(movies)
                            }
                        }
                    }

                } else {
                    onFailure(response.message())
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                onFailure(t.message ?: "")
            }

        })
    }

    override fun getMovieDetails(
        onSuccess: (MovieVO) -> Unit,
        onFailure: (String) -> Unit,
        movieId: String
    ) {
        mTheMovieApi?.getMovieDetails(movieId)?.enqueue(object : Callback<MovieDetailsResponse> {
            override fun onResponse(
                call: Call<MovieDetailsResponse>,
                response: Response<MovieDetailsResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.data?.let {
                        onSuccess(it)
                    }
                } else {
                    onFailure(response.message())
                }
            }

            override fun onFailure(call: Call<MovieDetailsResponse>, t: Throwable) {
                onFailure(t.message ?: "")
            }

        })
    }

    override fun getCinemaTimeslots(
        authorization: String,
        date: String,
        onSuccess: (List<CinemaVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieApi?.getCinemaTimeslot(authorization, date)
            ?.enqueue(object : Callback<CinemaTimeSlotResponse> {
                override fun onResponse(
                    call: Call<CinemaTimeSlotResponse>,
                    response: Response<CinemaTimeSlotResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.data?.let {
                            onSuccess(it)
                        }
                    }
                }

                override fun onFailure(call: Call<CinemaTimeSlotResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }

            })
    }

    override fun getConfig(onSuccess: (List<ConfigVO>) -> Unit, onFailure: (String) -> Unit) {
        mTheMovieApi?.getConfig()?.enqueue(object : Callback<ConfigResponse> {
            override fun onResponse(
                call: Call<ConfigResponse>,
                response: Response<ConfigResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.data.let {
                        if (it != null) {
                            onSuccess(it)
                        }
                    }
                } else {
                    onFailure(response.message())
                }
            }

            override fun onFailure(call: Call<ConfigResponse>, t: Throwable) {
                onFailure(t.message ?: "")
            }

        })
    }

    override fun getSeat(
        authorization: String,
        dayTimeSlotId: Int,
        bookingDate: String,
        onSuccess: (MutableList<MutableList<SeatVO>>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieApi?.getSeat(authorization, dayTimeSlotId, bookingDate)
            ?.enqueue(object : Callback<CinemaGetSeatResponse> {
                override fun onResponse(
                    call: Call<CinemaGetSeatResponse>,
                    response: Response<CinemaGetSeatResponse>
                ) {
                    if (response.isSuccessful) {
                        response?.body()?.data.let { seat ->
                            seat?.let { onSuccess(it) }

                        }

                    } else {
                        onFailure(response.message())
                    }

                }

                override fun onFailure(call: Call<CinemaGetSeatResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }

            })
    }

    override fun getSnack(
        authorization: String,
        categoryId: Int,
        onSuccess: (List<SnackVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieApi?.getSnack(authorization, categoryId)
            ?.enqueue(object : Callback<SnackResponse> {
                override fun onResponse(
                    call: Call<SnackResponse>,
                    response: Response<SnackResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.data?.let {
                            onSuccess(it)
                        }

                    } else {
                        onFailure(response.message())
                    }
                }

                override fun onFailure(call: Call<SnackResponse>, t: Throwable) {
                    onFailure(t.message ?: "")
                }

            })
    }

    override fun getSnackCategory(
        authorization: String,
        onSuccess: (List<SnackCategoryVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieApi?.getSnackCategory(authorization)?.enqueue(object :Callback<SnackCategoryResponse>{
            override fun onResponse(
                call: Call<SnackCategoryResponse>,
                response: Response<SnackCategoryResponse>
            ) {
                if (response.isSuccessful){
                    response.body()?.let {
                        onSuccess(it.data)
                    }
                }else{
                    onFailure(response.message())
                }
            }

            override fun onFailure(call: Call<SnackCategoryResponse>, t: Throwable) {
                onFailure(t.message?:"")
            }

        })
    }

    override fun getPayment(
        authorization: String,
        onSuccess: (List<PaymentVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieApi?.getPayement(authorization)?.enqueue(object :Callback<PaymentTypeResponse>{
            override fun onResponse(
                call: Call<PaymentTypeResponse>,
                response: Response<PaymentTypeResponse>
            ) {
                if (response.isSuccessful){
                    response.body()?.let {
                        it.data?.let { paymentList -> onSuccess(paymentList) }
                    }
                }else{
                    onFailure(response.message())
                }
            }

            override fun onFailure(call: Call<PaymentTypeResponse>, t: Throwable) {
                onFailure(t.message?:"")
            }

        })
    }

    override fun getTicketCheckout(
        authorization: String,
        ticketCheckout: CheckOutBody,
        onSuccess: (TicketCheckOutVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieApi?.getCheckOut(authorization,ticketCheckout)?.enqueue(object :Callback<CheckOutResponse>{
            override fun onResponse(
                call: Call<CheckOutResponse>,
                response: Response<CheckOutResponse>
            ) {

                if (response.isSuccessful){
                    response.body()?.data?.let{
                        onSuccess(it)
                    }
                }else{
                    onFailure(response.message())
                }

            }

            override fun onFailure(call: Call<CheckOutResponse>, t: Throwable) {
                onFailure(t.message?:"")
            }

        })
    }

    override fun getCinemaInfo(
        onSuccess: (List<CinemaInfoVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieApi?.getCinemaInfo()?.enqueue(object :Callback<CinemaResponse>{
            override fun onResponse(
                call: Call<CinemaResponse>,
                response: Response<CinemaResponse>
            ) {
                if (response.isSuccessful){
                    response.body()?.data?.let {
                        onSuccess(it)
                    }
                }else{
                    onFailure(response.message())
                }
            }

            override fun onFailure(call: Call<CinemaResponse>, t: Throwable) {
                onFailure(t.message?:"")
            }

        })
    }

    override fun logOut(
        authorization: String,
        onSuccess: (LogOutResponse) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieApi?.logOut(authorization)?.enqueue(object :Callback<LogOutResponse>{
            override fun onResponse(
                call: Call<LogOutResponse>,
                response: Response<LogOutResponse>
            ) {
                if (response.isSuccessful){
                    response.body()?.let(onSuccess)

                }else{
                    onFailure(response.message()?:"")
                }

            }

            override fun onFailure(call: Call<LogOutResponse>, t: Throwable) {
                onFailure(t.message?:"")
            }

        })
    }


}